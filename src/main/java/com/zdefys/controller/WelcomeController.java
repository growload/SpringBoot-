package com.zdefys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: zdefys
 * @date: 2020/5/25 18:21
 * @version: v1.0
 * @description:
 */
//@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        return "level1/"+path;
    }

    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        return "level2/"+path;
    }

    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        return "level3/"+path;
    }

}
