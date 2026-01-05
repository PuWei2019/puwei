package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImp implements IUserService {
    @Autowired private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //判断用户是否被构造
        //前端传递的user通过getusername方法得到注册名
        User result =userMapper.findByUsername(user.getUsername());
        if (result != null){
        //抛出异常
            throw new UsernameDuplicatedException("用户名:"+result.getUsername()+"被占用");
        }
        //密码加密MD5  盐值+密码+盐值->MD5算法加密三次

        String oldpassword =user.getPassword();
        //盐值的随机获取
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5passwod =getMD5Password(oldpassword,salt);

        //记录盐值
        user.setSalt(salt);
        //添加md5密码
        user.setPassword(md5passwod);
        //补全信息
        user.setIsDelete(0);
        //补全日志信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getModifiedUser());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //注册功能实现
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw  new InsertException("注册过程中发生了异常");
        }

    }

    @Override
    public User login(String username, String password) {
       User result = userMapper.findByUsername(username);
       //用户是否存在
       if (result == null || result.getIsDelete() == 1){
           throw new UserNotFoundException("用户不存在");
       }
       //判断密码 前端传过来的密码参数password->MD5加密->数据库里面密码比较

        //获取盐值前端传过来的密码加密
        String salt =result.getSalt();
        String MD5Password = getMD5Password(password,salt);

        //数据库里面密码比较
        if (!MD5Password.equals(result.getPassword())){
            throw  new PasswordNotMatchException("密码错误");
        }
        //传递目前需要的值,提高性能
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changPassword(Integer uid, String username, String oldPassword, String newPassword) {
        //查看用户是否存在
        User result =userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException();
        }

        //旧密码验证(前端传过来的是明文,需要加密后与数据库里面想匹配)
        String oldMd5Password =getMD5Password(oldPassword,result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("原密码不正确");
        }

        //新密码设置到数据库
        String newMd5Password =getMD5Password(newPassword,result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid,newMd5Password,result.getUsername(),new Date());
        if (rows != 1){
        throw new UpdateException("更新数据时产生异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        //点击个人资料按钮时查询所有信息
        User user = userMapper.findByUid(uid);
        //判断是否存在
        if (user == null || user.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        //只需提交要更新的信息
        User result = new User();
        result.setUsername(user.getUsername());
        result.setPhone(user.getPhone());
        result.setEmail(user.getEmail());
        result.setGender(user.getGender());
        return result;
    }

    @Override
    public void changeInfo(User user, Integer uid, String username) {
        //在个人资料页时提交修改之前时查询所有信息(防止cookie过期或者停留页面太久用户被删除)
        User result = userMapper.findByUid(uid);

        //判断是否存在
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1){
           throw new UpdateException("更新时发生错误");
        }
    }

    /**
     * 修改用户头像
     * @param uid
     * @param avatar 头像路径
     * @param username
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        //判断是否存在
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows != 1){
            throw new UpdateException("头像更新失败");
        }
    }

    /** 定义一个MD5算法*/
    private  String getMD5Password(String password, String salt){
        //MD5算法 String的etBytes()方法吧String类型转换为字节 utf-8
        for (int i=1;i<=3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes(StandardCharsets.UTF_8)).toUpperCase();
        }
        return password;
    }


}
