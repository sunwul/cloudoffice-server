package com.sunwul.cloudoffice.server.entity;

import java.time.LocalDateTime;

/*****
 * @author sunwul
 * @date 2021/3/28 19:43
 * @description WebSocket聊天消息
 */
public class ChatMsg {

    // 来自哪里
    private String from;
    // 去向何方
    private String to;
    // 聊天内容
    private String content;
    // 时间
    private LocalDateTime date;
    // 昵称 - 发送者
    private String formNickName;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFormNickName() {
        return formNickName;
    }

    public void setFormNickName(String formNickName) {
        this.formNickName = formNickName;
    }
}
