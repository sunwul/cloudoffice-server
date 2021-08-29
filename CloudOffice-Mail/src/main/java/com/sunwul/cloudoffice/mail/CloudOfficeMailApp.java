package com.sunwul.cloudoffice.mail;

import com.sunwul.cloudoffice.server.entity.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/*****
 * @author s/unwul
 * @date 2021/3/21 10:44
 * @description CloudOffice 邮件服务
 * exclude = {DataSourceAutoConfiguration.class} 去掉自动配置数据源
 * mail依赖了server,server设置了连接数据库, 当mail启动时会报设置DataSource失败
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CloudOfficeMailApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudOfficeMailApp.class);
    }

    /**
     * 创建一个队列
     * 队列名就是CloudOffice-Server发送消息的时候设置的路由键
     *
     * @return 队列
     */
//    @Bean
//    public Queue queue() {
//        return new Queue(MailConstants.MAIL_QUEUE_NAME);
//    }
}
