package com.zdefys.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: zdefys
 * @date: 2020/5/25 18:28
 * @version: v1.0
 * @description:
 */
//@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设定角色的权限 (角色和资源)
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能
        http.formLogin().usernameParameter("username").passwordParameter("password")
                .loginPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设定 用户  密码 及 角色的关联关系
        // security 要求用户登录时，密码必须加密
        String pwd = new BCryptPasswordEncoder().encode("123456");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder() {
        })
                .withUser("root").password(pwd).roles("VIP1", "VIP2", "VIP3")
                .and()
                .withUser("guest").password(pwd).roles("VIP1")
                .and()
                .withUser("student").password(pwd).roles("VIP1", "VIP2");
    }
}
