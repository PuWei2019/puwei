package com.cy.store.controller;

import com.cy.store.service.ICartsService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController{
    @Autowired
    private ICartsService iCartsService;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void> addToCart(HttpSession session,Integer pid,Integer amount){

        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        iCartsService.addToCart(uid,pid,amount,username);
        return new JsonResult<>(SUCCESS,null);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        //通过 session 得到uid
        Integer uid = getUidFromSession(session);
        //通过 uid得到购物车和商品信息
        List<CartVO> data = iCartsService.getVOByUid(uid);

        return new JsonResult<>(SUCCESS,data);
    }


    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid")Integer cid, HttpSession session){
        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //得到商品的数量
        Integer data = iCartsService.addNum(cid,uid,username);

        return new JsonResult<>(SUCCESS,data);
    }

    @RequestMapping("/{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid")Integer cid, HttpSession session){
        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //得到商品的数量
        Integer data = iCartsService.reduceNum(cid,uid,username);

        return new JsonResult<>(SUCCESS,data);
    }
}
