package com.cy.store.mapper;

import com.cy.store.entity.Address;
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
public class AddressMapperTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired  AddressMapper addressMapper;
  @Test
    public void insertAddress(){
      Address address =new Address();
      address.setUid(77);
      address.setName("田中");
      address.setPhone("18888888888");
      addressMapper.InsertAddress(address);
  }
    @Test
    public void countByUid(){
      Integer rows = addressMapper.CountByUid(4);
      System.out.println(rows);
    }

  @Test
  public void findByUid(){
    List<Address> lists = addressMapper.findByUid(2);
    for (Address list : lists){
      System.out.println(list);
    }
  }
  @Test
  public void  findByAid(){
    System.out.println(addressMapper.findByAid(4));
  }

  @Test
  public void  updateNonDefault(){
    addressMapper.updateNonDefault(1);
  }

  @Test
  public void updateDefaultByAid(){
    addressMapper.updateDefaultByAid(1,"PU桑",new Date());
  }
  @Test
  public void deleteByAid(){
    addressMapper.deleteByAid(7);
  }
  @Test
  public void findLastModified(){
    Address address =addressMapper.findLastModified(9);
    System.out.println(address);
  }
}
