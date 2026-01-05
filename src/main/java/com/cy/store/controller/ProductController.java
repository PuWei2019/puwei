package com.cy.store.controller;

import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    /**
     * 查询热销商品
     * @return
     */
    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        //查询热销商品
    List<Product> data = productService.findHotList();
        //返回前端
        return new JsonResult<>(SUCCESS,data);
    }

    @RequestMapping("/{id}/details")
    public JsonResult<Product> getProductDetails(@PathVariable("id") Integer id){
        Product details = productService.getById(id);
        return new JsonResult<>(SUCCESS,details);
    }
}
