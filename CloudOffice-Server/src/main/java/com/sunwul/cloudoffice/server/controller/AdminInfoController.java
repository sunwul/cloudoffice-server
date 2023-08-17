package com.sunwul.cloudoffice.server.controller;

import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.utils.FastDFSUtils;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/*****
 * @author sunwul
 * @date 2021/3/28 20:18
 * @description 个人中心
 */
@RestController
public class AdminInfoController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public ResponseBean updateAdmin(@RequestBody Admin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            // 将最新的用户信息写入security全局上下文
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    admin,
                    null,
                    authentication.getAuthorities()));
            return ResponseBean.success("更新当前用户信息成功!");
        }
        return ResponseBean.error("更新当前用户信息失败!");
    }

    @ApiOperation(value = "更新当前用户密码")
    @PutMapping("/admin/pass")
    public ResponseBean updateAdminPass(@RequestBody Map<String, Object> info) {
        // 旧密码
        String oldPass = (String) info.get("oldPass");
        // 新密码
        String newPass = (String) info.get("pass");
        // 操作员id
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass, newPass, adminId);
    }

    @ApiOperation(value = "更新当前用户头像")
    @PostMapping("/admin/userface")
    public ResponseBean updateAdminUserFace(MultipartFile file, Integer id, Authentication authentication) {
        String[] filePath = FastDFSUtils.upload(file);
        // 获取文件完整路径
        String url = FastDFSUtils.getTrackerUrl() + filePath[0] + "/" + filePath[1];
        // 更新用户头像
        return adminService.updateAdminUserFace(url, id, authentication);
    }

}
