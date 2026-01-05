package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//@SpringBootTest表示当前为测试类 不会随着项目打包
@SpringBootTest
//@RunWith表示启动单元测试类,需要传递一个参数SpringRunner.class
//@RunWith(SpringRunner.class)
public class UserServiceTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired
    IUserService userService;
    /**
     * 1.必须被@Test注释修饰
     * 2.返回值必须是viod
     * 3.方法不指定任何类型
     * 4.修饰方法必须是public
     */
    @Test
    public void reg(){
        try {
            User user=new User();
            user.setUsername("test2");
            user.setPassword("123");
            userService.reg(user);
            System.out.print("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
       User user = userService.login("test1","123");
        System.out.println(user);
    }
    @Test
    public void changPassword(){
        userService.changPassword(9,"test2","123","321");
        System.out.println("ok");
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(9,"/update/avatar","管理人");
    }
}
