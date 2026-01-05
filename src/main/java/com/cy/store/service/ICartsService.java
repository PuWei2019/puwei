package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

public interface  ICartsService {

    /**
     * 购买商品添加到购物车
     * @param uid 用户 id
     * @param pid 商品 id
     * @param amount 商品数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer amount, String username);

    /**
     *通过用户查询购物车信息
     * @param uid 用户 ID
     * @return 购物车商品VO 数据
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 增加用户购物车数量
     * @param cid 购物车 id
     * @param uid 用户 id
     * @param username 用户名
     * @return 购物车商品新的数量
     */
    Integer addNum(Integer cid,Integer uid, String username);

/**
 * 减少用户购物车数量
 * @param cid 购物车 id
 * @param uid 用户 id
 * @param username 用户名
 * @return 购物车商品新的数量
 */
    Integer reduceNum(Integer cid,Integer uid, String username);


}
