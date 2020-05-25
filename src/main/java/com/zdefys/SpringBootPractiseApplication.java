package com.zdefys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// 声明这是SpringBoot项目
// 这个类是主程序入口
@SpringBootApplication
// 打开定时任务的使用
@EnableScheduling
// 增加异步任务的开关
@EnableAsync
@MapperScan("com.zdefys.mapper")
public class SpringBootPractiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPractiseApplication.class, args);
    }

}
