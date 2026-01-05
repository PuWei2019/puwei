package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     * 插入购物车
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新某件商品数量
     * @param cid 购物车id
     * @param num 更新数量
     * @param modifiedUser 更新用着
     * @param modifiedTime 更新时间
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     *根据用户 ID 和商品 ID查询购物车数据
     * @param uid 用户 ID
     * @param pid 商品 ID
     * @return 购物车数据
     */
    Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     *通过用户查询购物车信息
     * @param uid 用户 ID
     * @return VO数据
     */
    List<CartVO> findVOByUid(Integer uid);


    /**
     * 根据 CID 查询购物车信息
     * @param cid
     * @return 购物车信息
     */
    Cart findByCid(Integer cid);

    /**
     * 查询所有提交结算购物车的 页面Vo数据
     * @param cids 提交结算的所有购物车 cid
     * @return VO数据
     */
    List<CartVO> findVOByCid(Integer[] cids);
}
