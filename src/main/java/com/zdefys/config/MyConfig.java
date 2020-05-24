package com.zdefys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author: zdefys
 * @date: 2020/5/24 18:19
 * @version: v1.0
 * @description:
 */
@Configuration
public class MyConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
