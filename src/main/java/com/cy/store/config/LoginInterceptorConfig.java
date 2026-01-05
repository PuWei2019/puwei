package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//拦截器的注册（注册之后才能生效和使用拦截器）
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 或者LoginInterceptor类加上@Component交给spring
         * 通过@Autowired进行管理
         */
        HandlerInterceptor interceptor =new LoginInterceptor();

        //添加能通过的页面和资源以及请求方法
        List<String> list = new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/login.html");
        list.add("/web/registry.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/users/login");
        list.add("/users/reg");
        //测试用
        list.add("/districts/**");
        //热销商品接口
        list.add("/products/**");



        //连接器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(list);



    }
}
