package com.sunwul.cloudoffice.server.controller;

import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.AdminLoginParam;
import com.sunwul.cloudoffice.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/*****
 * @author sunwul
 * @date 2021/3/21 13:34
 * @description 登录控制器
 */
//@Api(value = "LoginController", tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录之后返回token", notes = "根据用户名密码获取token信息")
    @PostMapping("/login")
    public ResponseBean login(@RequestBody AdminLoginParam loginParam, HttpServletRequest request) {
        return adminService.login(loginParam.getUsername(), loginParam.getPassword(), loginParam.getCode(), request);
    }

    @ApiOperation(value = "获取当前登录用户信息", notes = "登录成功时,从security全局上下文中获取用户名,并通过用户名获取完整用户对象")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        // 在登录成功时,已经将登录的用户对象放在security的全局上下文中,此时可以直接使用Principal获取登录信息
        String username = principal.getName();
        // 通过用户名获取完整用户对象
        Admin admin = adminService.getAdminByUserName(username);
        // 出于安全考虑,不返回密码给前端
        admin.setPassword(null);
        // 写入用户所拥有的角色  getRoles() ->根据用户id查询角色列表
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出登录", notes = "前端直接调用并自己处理逻辑,后端不做处理")
    @PostMapping("/logout")
    public ResponseBean logout() {
        return ResponseBean.success("登录已注销!");
    }
}
