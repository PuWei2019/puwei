package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     *根据父类代号（例如省86 获得所有省）获取区的所有信息
     * @param parent 父类代号
     * @return  所有区的集合
     */
    List<District> findByParent(String  parent);

    /**
     *通过 code找到省市区的名字
     * @param code 省市区代码
     * @return 市区的名字
     */
    String findNameByCode(String code);
}
