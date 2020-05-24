package com.zdefys;

import com.zdefys.handler.MailHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootPractiseApplicationTests {

    @Autowired
    private MailHandler mailHandler;

    @Test
    void contextLoads() throws Exception {
//        mailHandler.send();
        mailHandler.sendByTemplate();
    }

}
