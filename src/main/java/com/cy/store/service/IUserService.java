package com.cy.store.service;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {
    /**
     * 注册方法
     * @param user
     */
    void reg(User user);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changPassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据uid获取用户信息
     * @param uid
     * @return 用户信息
     */
    User getByUid(Integer uid);

    /**
     * 修改用户信息
     * @param user
     * @param uid
     * @param username
     */
    void changeInfo(User user,Integer uid,String username);

    void changeAvatar(Integer uid, String avatar, String username);
}
