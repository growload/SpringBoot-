package com.zdefys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 声明这是SpringBoot项目
// 这个类是主程序入口
@SpringBootApplication
@EnableScheduling
public class SpringBootPractiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPractiseApplication.class, args);
    }

}
