package com.zdefys.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zdefys
 * @date: 2020/5/24 21:02
 * @version: v1.0
 * @description:
 */
@Component
public class MailHandler {

    @Autowired
    private JavaMailSender mailSender;

    public void send(){
        System.out.println("执行邮件发送逻辑");
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("来自德恩的问候");
        mailMessage.setText("不要爱上我");
        mailMessage.setTo("deenzhang187@126.com");
        mailMessage.setFrom("571597969@qq.com");

        mailSender.send(mailMessage);
    }

    @Autowired
    private TemplateEngine templateEngine;

    // 结合模板使用
    @Async
    public void sendByTemplate() throws Exception{
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
        helper.setSubject("来自笔记的问候");

        Context context = new Context();
        Map<String, Object> map = new HashMap<>(2);
        map.put("content", "我要爱上你");
        context.setVariables(map);

        String result = this.templateEngine.process("mail", context);
        // 设置文本 且html标志为true
        helper.setText(result,true);
        helper.setTo("deenzhang187@126.com");
        helper.setFrom("571597969@qq.com");
        // 添加附件
        String filePath = "SpringBoot项目.md";
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        helper.addAttachment("笔记",fileSystemResource);
        mailSender.send(mailMessage);
    }
}
