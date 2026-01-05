package com.cy.store.mapper;
import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

//@SpringBootTest表示当前为测试类 不会随着项目打包
@SpringBootTest
//@RunWith表示启动单元测试类,需要传递一个参数SpringRunner.class
//@RunWith(SpringRunner.class)
public class UserMapperTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired UserMapper userMapper;
    /**
     * 1.必须被@Test注释修饰
     * 2.返回值必须是viod
     * 3.方法不指定任何类型
     * 4.修饰方法必须是public
     */
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123");
        Integer rows =userMapper.insert(user);
        System.out.print(rows);
    }
    @Test
    public void findByUsername(){
       User user = userMapper.findByUsername("tom");
       System.out.println(user);
    }
    @Test
    public void updatePasswordByUid(){
       userMapper.updatePasswordByUid(8,"321","系统管理员",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(8));
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(9);
        user.setPhone("123456789");
        user.setEmail("test2@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
}
