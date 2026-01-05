package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartsService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartService implements ICartsService {
    /*商品信息可以通过商品接口查询或者前端发送  本次通过调用商品mapper接口查询*/

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     *添加到购物车
     * @param uid      用户 id
     * @param pid      商品 id
     * @param amount   商品新加数量
     * @param username 用户名
     */
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //当前要添加的商品
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        //积累操作时间
        Date date = new Date();
        //如果当前商品没有被添加到购物车中
        if (result == null) {
            /* 进行购物车新增操作*/
            //查询商品信息
            Product product = productMapper.findById(pid);
            // 创建一个购物车
            Cart cart = new Cart();
            //补全数据
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setNum(amount);
            cart.setPrice(product.getPrice());
            cart.setCreatedTime(date);
            cart.setCreatedUser(username);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            //插入购物车
            Integer rows = cartMapper.insert(cart);
            if (rows == null) {
                throw new InsertException("插入购物车数据时产生异常");
                //当前商品已经存在在购物车时 更新数量
            }

        } else {
            //商品总数
            Integer num = result.getNum() + amount;
            Integer row = cartMapper.updateNumByCid(
                    result.getCid(),
                    num,
                    username,
                    date);
            if (row != 1) {
                throw new UpdateException("更新购物车数据时产生异常");
            }
        }

    }

    /**
     *通过用户查询购物车信息
     * @param uid 用户 ID
     * @return 购物车商品VO 数据
     */
    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        List<CartVO> cartVOS = cartMapper.findVOByUid(uid);
        return cartVOS;
    }

    /**
     * 更新商品数
     * @param cid 购物车 id
     * @param uid 用户 id
     * @param username 用户名
     * @return 购物车商品数
     */
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        //查询购物车数据
       Cart result = cartMapper.findByCid(cid);
       //判断购物车数据是否存在
        if (result == null) {
            throw  new CartNotFoundException("购物车数据不存在");
        }
        //判断是否属于当前用户
        if (!uid.equals(result.getUid())) {
            throw new AccessDeniedException("数据非法访问");
        }
        //记录购物车商品数

        Integer num = result.getNum() + 1;
        //跟新商品数量
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if (rows !=1) {
            throw new UpdateException("更新购物车数据失败");
        }
        //返回商品数量
        return num;

    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        //查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        //判断购物车数据是否存在
        if (result == null) {
            throw  new CartNotFoundException("购物车数据不存在");
        }
        //判断是否属于当前用户
        if (!uid.equals(result.getUid())) {
            throw new AccessDeniedException("数据非法访问");
        }
        //记录购物车商品数
        Integer num = result.getNum() - 1;
        //如果减少到0 则返回数量1
        if (num == 0){
            return 1;
        }
        //跟新商品数量
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if (rows !=1) {
            throw new UpdateException("更新购物车数据失败");
        }
        //返回商品数量
        return num;

    }

}
