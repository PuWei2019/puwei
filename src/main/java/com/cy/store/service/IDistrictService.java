package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     *根据省代号获取区的所有信息
     * @param parent
     * @return  所有区的集合
     */
    List<District> getByParent(String parent);

    /**
     * 通过省市区code获取对应名字
     * @param code 省市区code
     * @return 省市区名字
     */
    String getNameByCode(String code);
}
