package com.cy.store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class UploadAvatarConfig implements WebMvcConfigurer {
    @Value("${store.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                //必须加"file:" 表示让spring去找系统文件
                //D:/upload/	classpath 资源	找不到路径 ×
                //file:D:/upload/	文件系统路径	正常访问 ✓
                .addResourceLocations("file:" + uploadPath);
    }
}
