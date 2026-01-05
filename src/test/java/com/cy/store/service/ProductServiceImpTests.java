package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


//@SpringBootTest表示当前为测试类 不会随着项目打包
@SpringBootTest
//@RunWith表示启动单元测试类,需要传递一个参数SpringRunner.class
//@RunWith(SpringRunner.class)
public class ProductServiceImpTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired
    private IProductService productService;

    @Test
    public void findHotList(){
        System.out.println(productService.findHotList());
    }


}
