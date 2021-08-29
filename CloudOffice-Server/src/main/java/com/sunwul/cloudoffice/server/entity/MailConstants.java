package com.sunwul.cloudoffice.server.entity;

/*****
 * @author sunwul
 * @date 2021/3/28 12:51
 * @description 消息状态
 */
public class MailConstants {

    // 消息投递中
    public static final Integer DELIVERING = 0;

    // 消息投递成功
    public static final Integer SUCCESS = 1;

    // 消息投递失败
    public static final Integer FAILURE = 2;

    // 消息投递最大尝试次数
    public static final Integer MAX_TRY_COUNT = 3;

    // 消息投递超时时间  一分钟
    public static final Integer MSG_TIMEOUT = 1;

    // 消息队列
    public static final String MAIL_QUEUE_NAME = "sunwul.mail.welcome";

    // 交换机
    public static final String MAIL_EXCHANGE_NAME = "sunwul.mail.exchange";

    // 路由键
    public static final String MAIL_ROUTING_KEY_NAME = "sunwul.mail.routing.key";

}
