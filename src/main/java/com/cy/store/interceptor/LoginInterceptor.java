package com.cy.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    //HttpServletRequest请求对象
    //HttpServletResponse 返回对象

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //HttpServletRequest请求对象中获取session
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null){
            //没有session说明没有登陆，重新定向到登陆页面
            response.sendRedirect("/web/login.html");
            //结束请求
            return false;
        }
        //放行请求
        return true;
    }
}
