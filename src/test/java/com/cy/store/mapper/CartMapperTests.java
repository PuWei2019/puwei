package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

//@SpringBootTest表示当前为测试类 不会随着项目打包
@SpringBootTest


//@RunWith(SpringRunner.class)
//@RunWith表示启动单元测试类,需要传递一个参数SpringRunner.class
//让 JUnit4 的测试运行在 Spring 环境里。
//如果没有它：
//@Autowired 全部注入失败（为 null）
//@SpringBootTest 也不会生效
// Spring 容器不会启动
// 测试类只能执行普通Java测试
public class CartMapperTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(9);
        cart.setPid(10000001);
        cart.setNum(2);
        cart.setPrice(9000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 3, "管理员", new Date());
    }

    @Test
    public void findByUidAndPid() {
        cartMapper.findByUidAndPid(9, 10000001);
    }

    @Test
    public void findVOByUid() {
        System.out.println(cartMapper.findVOByUid(9));
    }

    @Test
    public void  findByCid(){
        System.out.println(cartMapper.findByCid(9));
    }



    @Test
    public void  findVOByCid(){
        Integer[] cids = {1,2};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
