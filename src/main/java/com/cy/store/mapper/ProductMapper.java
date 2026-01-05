package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    //查询畅销列表
    List<Product> findHotList();

    //查询商品详细信息
    Product findById(Integer id);
}
