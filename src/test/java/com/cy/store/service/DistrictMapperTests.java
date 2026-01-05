package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class DistrictMapperTests {
    //idea有检测功能,接口是不能直接创建bean(动态代理)
    //解决方法降低权限设置中inspections->spring->spring core->Autowiring for bean class权限调节成warning
    @Autowired
    private IDistrictService iDistrictService;
  @Test
    public void getByParent(){
      //获取所有省份 通过中国代号
    List<District> lists = iDistrictService.getByParent("86");
      for (District list : lists){
        System.out.println(list);
      }
  }




}
