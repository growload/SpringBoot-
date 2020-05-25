package com.zdefys.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zdefys
 * @date: 2020/5/25 11:56
 * @version: v1.0
 * @description: 自定义拦截器
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        // 已登录 放行
        if (user != null) {
            return true;
        }
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
