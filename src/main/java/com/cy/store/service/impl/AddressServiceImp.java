package com.cy.store.service.impl;

import com.cy.store.controller.ex.AddressCountLimitException;
import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImp implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    //添加用户收货地址业务层依赖于 IDistrictService的业务接口
    @Autowired
    private IDistrictService iDistrictService;

    /**
     * 插入地址
     *
     * @param uid      用户id
     * @param username 登陆用户
     * @param address  地址数据
     */
    @Override
    public void AddNewAddress(Integer uid, String username, Address address) {
        //查询用户地址有多少条
        Integer count = addressMapper.CountByUid(uid);
        //如果大于设置条数
        if (count >= maxCount) {
            //抛出异常
            throw new AddressCountLimitException("用户地址超出上线");
        }
        //如果没有大于默认地址
        //补全用户信息
        String provincecode = iDistrictService.getNameByCode(address.getProvinceCode());
        String citycode = iDistrictService.getNameByCode(address.getCityCode());
        String areacode = iDistrictService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provincecode);
        address.setCityName(citycode);
        address.setAreaName(areacode);

        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0; //如果查询出来的用户地址条数为0则设默认地址(isDefault=1)否则不设为默认(isDefault=0)
        address.setIsDefault(isDefault);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);//
        address.setCreatedUser(username);
        Integer rows = addressMapper.InsertAddress(address);
        if (rows != 1) {
            throw new InsertException("插入地址失败发生未知异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> lists = addressMapper.findByUid(uid);
        for (Address list : lists) {
            list.setUid(null);
            list.setProvinceCode(null);
            list.setCityCode(null);
            list.setAreaCode(null);
            list.setTel(null);
            list.setIsDefault(null);
            list.setCreatedTime(null);
            list.setCreatedUser(null);
            list.setModifiedTime(null);
            list.setModifiedUser(null);
        }
        return lists;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        //查询地址是否存在
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //判断地址是否属于当前用户
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        //设置用户所有收货地址为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新时产生异常");
        }
        //将用户某个地址设置为默认地址
        Integer row = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (row != 1) {
            throw new UpdateException("更新时产生异常");
        }


    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        //查询地址
        Address address = addressMapper.findByAid(aid);
        //判断是否存在
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //判断是否属于当前用户
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }
        //删除地址
        Integer row = addressMapper.deleteByAid(aid);
        if (row != 1) {
            throw new DeleteException("删除数据产生异常");
        }
        //删除了还剩几条地址
        Integer count = addressMapper.CountByUid(uid);
        if (count == 0) {
            //没有地址则不执行任何操作
            return;
        }
        //如果删除默认地址则设置最新操作的数据并且设置为默认地址
        if (address.getIsDefault() == 1) {
            //找到用户下最新地址
            Address LastModified = addressMapper.findLastModified(uid);
            //设置为默认地址
            Integer default_row = addressMapper.updateDefaultByAid(LastModified.getAid(), username, new Date());
            if (default_row != 1) {
                throw new UpdateException("更新地址产生异常");

            }

        }

    }


}
