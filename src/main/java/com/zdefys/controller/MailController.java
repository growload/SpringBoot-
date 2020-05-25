package com.zdefys.controller;

import com.zdefys.handler.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zdefys
 * @date: 2020/5/25 16:02
 * @version: v1.0
 * @description:
 */
@RestController
public class MailController {

    @Autowired
    private MailHandler mailHandler;

    @GetMapping("/async")
    public String async() {
        try {
            mailHandler.sendByTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "suucess";
    }
}
