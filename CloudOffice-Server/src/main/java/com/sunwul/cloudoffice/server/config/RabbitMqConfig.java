package com.sunwul.cloudoffice.server.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sunwul.cloudoffice.server.entity.MailConstants;
import com.sunwul.cloudoffice.server.entity.MailLog;
import com.sunwul.cloudoffice.server.service.MailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author sunwul
 * @date 2021/3/28 13:22
 * @description RabbitMQ配置类
 */
@Configuration
public class RabbitMqConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);

    // 注入连接工厂
    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MailLogService mailLogService;

    // 设置RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /**
         * 消息确认回调, 确认消息是否到达*
         * data: 消息唯一标识
         * ack: 确认结果
         * cause: 失败原因
         */
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                log.info("{}===>消息发送成功", msgId);
                // 根据msgId去更新消息投递状态为成功
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", MailConstants.SUCCESS).eq("msgId", msgId));
            } else {
                log.info("{}===>消息发送失败", msgId);
            }
        });
        /**
         * 消息失败回调
         * msg: 消息主题
         * repCode: 响应码
         * repText: 响应描述
         * exchange: 交换机
         * routingKey: 路由键
         */
        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingKey) -> {
            log.error("消息发送queue时失败", msg.getBody());
        });
        return rabbitTemplate;
    }

    /**
     * 设置队列
     *
     * @return Queue
     */
    @Bean
    public Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    /**
     * 设置交换机
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    /**
     * 绑定队列 交换机 路由键
     *
     * @return Binding
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
