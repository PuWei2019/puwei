package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户注册语句
 * 1.插入数据 insert into t_user(username,password) values (值)
 * 2.查询当前用户名 select * from t_user where username=?
 */
//@Mapper 不要每一个Mapper接口都添加注解  在启动类直接指定
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user
     * @return 插入行数(增，删，改 根据返回值是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名字查询数据
     * @param username
     * @return 找到返回用户 没有找到返回null
     */
    User findByUsername(String username);

    /**
     * 通过uid修改用户密码
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return 修改条数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 通过uid看用户是否存在
      * @param uid
     * @return 目标用户返回值
     */
    User findByUid(Integer uid);

    /**
     * 更新个人信息
     * @param user
     * @return 修改条数
     */
    Integer updateInfoByUid(User user);


    /**
     * @Param("#SQL占位符的变量名字")注解可以将SQL语句占位符和接口参数不一致
     * 需要将某一个参数强行注入到某个占位符
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param("uid")Integer uid,
                              @Param("avatar")String avatar,
                              @Param("modifiedUser")String modifiedUser,
                              @Param("modifiedTime")Date modifiedTime);

}
