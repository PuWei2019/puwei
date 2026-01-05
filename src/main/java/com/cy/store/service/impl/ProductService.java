package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询热销商品
     *
     * @return 热销商品
     */
    @Override
    public List<Product> findHotList() {
        List<Product> lists = productMapper.findHotList();
        //forEach只操作数据不返回任何值或者类型
        //去除多余数据
        lists.forEach(list -> {
                    list.setPriority(null);
                    list.setModifiedTime(null);
                    list.setModifiedUser(null);
                    list.setCreatedTime(null);
                    list.setCreatedUser(null);
                }
        );

        //用map操作 返回集合
//        List<Product> newlist =lists.stream().map(list ->{
//            list.setPriority(null);
//            list.setModifiedTime(null);
//            list.setModifiedUser(null);
//            list.setCreatedTime(null);
//            list.setCreatedUser(null);
//            return list;
//        }).collect(Collectors.toList());
        return lists;
    }

    /**
     * 查询商品信息
     *
     * @param id 商品 ID
     * @return 商品信息
     */
    @Override
    public Product getById(Integer id) {
        //查询商品
        Product details = productMapper.findById(id);
        if (details == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        details.setPriority(null);
        details.setCreatedUser(null);
        details.setCreatedTime(null);
        details.setModifiedUser(null);
        details.setModifiedTime(null);
        return details;
    }
}
