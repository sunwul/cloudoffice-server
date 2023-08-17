package com.sunwul.cloudoffice.mail;

import com.rabbitmq.client.Channel;
import com.sunwul.cloudoffice.server.entity.Employee;
import com.sunwul.cloudoffice.server.entity.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/*****
 * @author sunwul
 * @date 2021/3/27 22:28
 * @description rabbitMQ消息接收
 */
@Component
public class MailReceiver {

    private static final Logger log = LoggerFactory.getLogger(MailReceiver.class);

    // 注入邮件发送
    @Autowired
    private JavaMailSender mailSender;

    // 注入邮件配置
    @Autowired
    private MailProperties mailProperties;

    // 注入thymeleaf引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 监听队列名为MailConstants.MAIL_QUEUE_NAME的消息
     * CloudOffice-Server发送消息的时候设置了路由键,就是这里的队列名
     * <p>
     * 普通的消息发送
     *
     * @param employee 接收到的消息传递对象
     */
//    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void sendMail(Employee employee) {
        // 创建消息
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        setupMail(helper, employee);
        // 发送邮件
        mailSender.send(mimeMessage);
        log.info("邮件已发送至[" + employee.getName() + "](" + employee.getEmail() + ").");
    }

    /**
     * 监听队列名为MailConstants.MAIL_QUEUE_NAME的消息
     * CloudOffice-Server发送消息的时候设置了路由键,就是这里的队列名
     * <p>
     * 消息的幂等性处理 - 已被消费的消息不会被二次消费
     * <p>
     * 流程如下:
     * 1. 接收到消息时先去redis中确认特定key的hash的key是否存在
     * 1.1 不存在时,正常走流程: 发送消息-->将已发送的消息的ID作为redis中特定key的hash的key存入redis
     * 1.2 存在时, 消息已经被消费, 手动确认消息
     * 2. 当出现异常时, 根据我们的设置手动确认消息,并且设置消息没有被确认时是否退回到队列
     *
     * @param message 消息
     * @param channel 信道
     */
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler2(Message message, Channel channel) {
        // 从消息中拿到员工类
        Employee employee = (Employee) message.getPayload();
        MessageHeaders header = message.getHeaders();
        // 消息序号  tag需要我们手动确认的时候返回
        long tag = (long) header.get(AmqpHeaders.DELIVERY_TAG);
        // 获取msgId   固定值; spring_returned_message_correlation
        String msgId = (String) header.get("spring_returned_message_correlation");
        // 存入redis
        // 使用hash,因为我们自己准备了一个redis的key,hash的key就是msgId,hash的value无所谓,我们主要需要的就是msgId
        HashOperations hashOperations = redisTemplate.opsForHash();
        try {
            // 当再次接收到一模一样的消息, 判断redis中key为mail_log的hash的key是否存在
            if (hashOperations.entries("mail_log").containsKey(msgId)) {
                log.info("消息已经被消费====>{}", msgId);
                /**
                 * 手动确认消息
                 * tag: 消息序号
                 * multiple: 是否确认多条
                 * */
                channel.basicAck(tag, false);
                return;
            }
            // 创建消息
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            // 设置邮件内容
            setupMail(helper, employee);
            // 发送邮件
            mailSender.send(mimeMessage);
            log.info("邮件已发送至[" + employee.getName() + "](" + employee.getEmail() + ").");
            // 将消息ID存入redis
            // 前面90行, 这里自定义一个key,hash的key就是msgId,hash的value随意
            // 这里的重点就是将msgId存进去
            hashOperations.put("mail_log", msgId, "OK");
            // 手动确认消息   是否一次性确认多条: false
            channel.basicAck(tag, false);
        } catch (IOException e) {
            try {
                /**
                 * 手动确认消息
                 * tag: 消息序号
                 * multiple: 是否确认多条
                 * requeue: 消息没有被确认时是否退回到队列
                 */
                channel.basicNack(tag, false, true);
            } catch (IOException ioException) {
                log.error("邮件发送失败====>{}", e.getMessage());
            }
            log.error("邮件发送失败====>{}", e.getMessage());
        }
    }

    /**
     * 设置邮件内容
     *
     * @param employee 员工信息
     */
    private void setupMail(MimeMessageHelper helper, Employee employee) {
        try {
            // 设置发件人
            helper.setFrom(mailProperties.getUsername());
            // 设置收件人  设置员工的email为收件人
            helper.setTo(employee.getEmail());
            // 设置主题
            helper.setSubject(employee.getName() + " 你好,欢迎加入XXX公司");
            // 设置发送日期  当前日期
            helper.setSentDate(new Date());
            // 模板内容
            Context context = new Context();
            // 设置内容属性
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("jobLevelName", employee.getJoblevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            // 通过thymeleaf引擎去拿到mail.html模板, 将值放进去
            String mail = templateEngine.process("mail", context);
            // 设置发送内容  true: 确定是否为HTML格式
            helper.setText(mail, true);
        } catch (MessagingException e) {
            log.error("邮件内容设置异常!==>{}", e.getMessage());
        }
    }

}
