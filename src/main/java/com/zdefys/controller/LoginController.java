package com.zdefys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: zdefys
 * @date: 2020/5/25 11:32
 * @version: v1.0
 * @description:
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 如果使用spring-secutiry 需要注释这部分代码
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session) {
        // 只要用户名不为空，且密码符合要求
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 用session存储登录状态
            session.setAttribute("loginUser",username);
            // 登录成功跳转首页
            return "redirect:/list/";
        }
        return "login";
    }
}
