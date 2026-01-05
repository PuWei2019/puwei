package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService iAddressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //插入地址
        iAddressService.AddNewAddress(uid, username, address);
        //返回前端信息
        return new JsonResult<>(SUCCESS, null);
    }

    // /address或者/address/都可以访问
    @RequestMapping({"/", ""})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        //通过 session 得到uid
        Integer uid = getUidFromSession(session);
        //得到用户所有地址信息
        List<Address> data = iAddressService.getByUid(uid);
        //返回前端地址和信息
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //设置默认地址
        iAddressService.setDefault(aid, uid, username);
        return new JsonResult<>(SUCCESS, null);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        //通过 session 得到uid 和 username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //删除地址
        iAddressService.delete(aid, uid, username);
        return new JsonResult<>(SUCCESS, null);
    }
}
