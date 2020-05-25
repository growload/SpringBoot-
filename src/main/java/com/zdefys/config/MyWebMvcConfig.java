package com.zdefys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zdefys
 * @date: 2020/5/25 12:01
 * @version: v1.0
 * @description:
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    // 注册自定义拦截器，声明相关拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/*.css","/*.js");

    }
}
