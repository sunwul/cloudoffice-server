package com.sunwul.cloudoffice.server.controller;

import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/28 20:00
 * @description 在线聊天
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords) {
        // 直接用前面定义的获取所有操作员来查
        return adminService.getAllAdmins(keywords);
    }
}
