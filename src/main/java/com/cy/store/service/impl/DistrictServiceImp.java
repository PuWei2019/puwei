package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImp implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;


    /**
     *根据省代号获取区的所有信息
     * @param parent
     * @return  所有区的集合
     */
    @Override
    public List<District> getByParent(String parent) {
        List<District> lists = districtMapper.findByParent(parent);
        /**
         * 在网络传输数据时尽量避免无效数据的传递 减少流量和提高性能
         */
        //不需要的数据清空为null
        for (District list : lists){
            list.setId(null);
            list.setParent(null);
        }
        return  lists;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
