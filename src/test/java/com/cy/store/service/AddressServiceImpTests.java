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
public class AddressServiceImpTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNew() {
        Address address = new Address();
        address.setName("蒲桑");
        address.setPhone("1666666666");
        addressService.AddNewAddress(88, "管理员", address);
    }

    @Test
    public void getByUid() {
        List<Address> lists = addressService.getByUid(9);
        for (Address list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void setDefault(){

        addressService.setDefault(5,9,"管理员");
    }
    @Test
    public void delete(){
        addressService.delete(6,9,"管理员");
    }

}
