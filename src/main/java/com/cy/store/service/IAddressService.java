package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

//收货地址接口
public interface IAddressService {

    /**
     * 插入新地址
     * @param uid 用户 ID
     * @param username 用户名
     * @param address 地址
     */
    void AddNewAddress(Integer uid, String username, Address address);

    /**
     * 得到用户所有地址
     * @param uid 用户 ID
     * @return 地址
     */
    List<Address> getByUid(Integer uid);

    /**
     * 修改地址为默认地址
     * @param aid 地址 ID
     * @param uid 用户 ID
     * @param username 修改用户
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除地址
     * @param aid 地址 ID
     * @param uid 用户 ID
     * @param username 用户名
     */
    void delete(Integer aid,Integer uid,String username);


}
