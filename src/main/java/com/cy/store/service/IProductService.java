package com.cy.store.service;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IProductService {
    //查询热销商品
    List<Product> findHotList();

    //查询商品信息
    Product getById(Integer id);
}
