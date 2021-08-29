package com.sunwul.cloudoffice.server.controller;

import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/*****
 * @author sunwul
 * @date 2021/3/28 19:47
 * @description WebSocket接口
 * 因为这里用的的不是restful风格的(Get/POST/PUT/DELETE)Mapping
 * 使用的是@MessageMapping, 所以不能用@RestController, 要用普通的@Controller
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {
        // 获取当前用户对象
        Admin admin = (Admin) authentication.getPrincipal();
        // 设置ChatMsg的from
        chatMsg.setFrom(admin.getUsername()); // 登录的用户名
        chatMsg.setFormNickName(admin.getName()); // 显示的用户姓名
        chatMsg.setDate(LocalDateTime.now()); // 时间
        // 发送消息  发送给谁,发送的地址,发送的消息
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
