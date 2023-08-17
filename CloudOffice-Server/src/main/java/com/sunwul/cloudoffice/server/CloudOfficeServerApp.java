package com.sunwul.cloudoffice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*****
 * @author sunwul
 * @date 2021/3/21 10:44
 * @description CloudOffice 服务端
 * * @EnableScheduling 开启定时任务
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.sunwul.cloudoffice.server.mapper")
public class CloudOfficeServerApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudOfficeServerApp.class);
    }
}
