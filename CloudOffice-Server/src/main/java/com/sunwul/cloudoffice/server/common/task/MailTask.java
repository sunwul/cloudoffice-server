package com.sunwul.cloudoffice.server.common.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sunwul.cloudoffice.server.entity.Employee;
import com.sunwul.cloudoffice.server.entity.MailConstants;
import com.sunwul.cloudoffice.server.entity.MailLog;
import com.sunwul.cloudoffice.server.service.EmployeeService;
import com.sunwul.cloudoffice.server.service.MailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/28 13:51
 * @description 邮件发送定时任务
 */
@Component
public class MailTask {

    @Autowired
    private MailLogService mailLogService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 生产端MQ消息投递可靠性
     * 使用定时任务查询消息发送的结果, 设置消息投递失败次数
     * 1. 在失败次数内消息投递成功,则改变消息投递状态为投递成功
     * 2. 超出失败次数的消息不再投递,改变消息投递状态为投递失败
     */
    @Scheduled(cron = "0/30 * * * * ?") // 每30秒请求一次,未投递成功的消息重新投递
    public void mailTask() {
        // 获取状态为0(投递中), 重试时间小于当前时间的消息
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>()
                .eq("status", MailConstants.DELIVERING)
                .lt("tryTime", LocalDateTime.now()));
        list.forEach((mailLog) -> {
            String msgId = mailLog.getMsgid();
            // 如果重试次数超过3次, 更新状态为投递失败
            if (mailLog.getCount() >= MailConstants.MAX_TRY_COUNT) {
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .eq("msgId", msgId)
                        .set("status", MailConstants.FAILURE)
                        .set("updateTime", LocalDateTime.now()));
            }
            // 否则更新重试次数加1,改变更新时间,重试时间
            mailLogService.update(new UpdateWrapper<MailLog>()
                    .eq("msgId", msgId)
                    .set("count", mailLog.getCount() + 1)
                    .set("updateTime", LocalDateTime.now())
                    .set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)));
            // 获取员工信息
            Employee employee = employeeService.getEmployee(mailLog.getEid()).get(0);
            // 重新发送信息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME,
                    employee, new CorrelationData(msgId));
        });
    }
}
