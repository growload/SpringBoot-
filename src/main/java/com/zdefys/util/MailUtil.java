package com.zdefys.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @author: zdefys
 * @date: 2020/5/24 20:17
 * @version: v1.0
 * @description:
 */
public class MailUtil {

    /**
     * 从qq邮箱 发送邮件  到126邮箱
     * ribogqlfpcgxbeaa
     */
    public static void send() throws Exception {
        // 1) 通过配置构成邮件的会话
        Properties prop = new Properties();
        // 设置传输协议
        prop.setProperty("mail.transport.protocol", "smtp");
        // smtp服务器地址
        prop.setProperty("mail.smtp.host", "smtp.qq.com");
        prop.setProperty("mail.smtp.auth", "true");
        // 配置SSL相关的信息
        String port = "465";
        prop.setProperty("mail.smtp.port", port);
        prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", port);
        // 2) 创建会话
        Session session = Session.getInstance(prop);
        // 3) 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        String sendMail = "571597969@qq.com";
        String recipients = "deenzhang187@126.com";
        message.setFrom(new InternetAddress(sendMail,"德恩","UTF-8"));
        // MimeMessage.RecipientType.CC 抄送 MimeMessage.RecipientType.BCC 密送
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(recipients,"德恩","UTF-8"));
        // 标题
        message.setSubject("来自ZDEFYS的问候","UTF-8");
        // 正文
        message.setContent("不要喜欢我","text/html;charset=UTF-8");
        // 发送时间
        message.setSentDate(new Date());
        // 可以保存为 *.eml的文件格式
        message.saveChanges();

        // 4） 获取邮件传输对象 建立连接并发送
        Transport transport = session.getTransport();
        // 设置账户和密码
        String account = "571597969@qq.com";
        String password = "ribogqlfpcgxbeaa";
        transport.connect(account,password);
        transport.sendMessage(message,message.getAllRecipients());
        // 关闭连接
        transport.close();
    }
}
