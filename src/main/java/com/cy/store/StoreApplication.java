package com.cy.store;

import jakarta.servlet.MultipartConfigElement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Configuration//表示配置类
@SpringBootApplication
@MapperScan("com.cy.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        //创建一个工场类
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        //设置相关信息
        //图片最大 10MB
        multipartConfigFactory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));

        //请求最大 15MB
        multipartConfigFactory.setMaxRequestSize(DataSize.of(15, DataUnit.MEGABYTES));

        return multipartConfigFactory.createMultipartConfig();

        //或者配置文件中加
        //spring.servlet.multipart.max-file-size=10MB
        //spring.servlet.multipart.max-request-size=15MB
    }
}
