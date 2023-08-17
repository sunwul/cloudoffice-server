package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.Role;
import com.sunwul.cloudoffice.server.service.AdminRoleService;
import com.sunwul.cloudoffice.server.service.AdminService;
import com.sunwul.cloudoffice.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sunwul
 * @since 2021-03-21
 * 操作员(管理员)
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    // 单表的CRUD mybatis-plus都已经封装好了,不需要去写ServiceImpl和mapper数据库操作
    // 对于多表的操作, mybatis-plus也支持,但是要比我们自己写SQL复杂, 所以此工程的多表操作都不用mybatis-plus实现

    @ApiOperation(value = "更新操作员信息")
    @PutMapping("/")
    public ResponseBean updateAdmin(@RequestBody Admin admin) {
        // 使用mybatis-plus的updateById根据ID更新数据,返回boolean类型
        if (adminService.updateById(admin)) {
            return ResponseBean.success("更新操作员信息成功!");
        }
        return ResponseBean.error("更新操作员信息失败!");
    }

    @ApiOperation(value = "删除操作员信息")
    @DeleteMapping("/{id}")
    public ResponseBean deleteAdmin(@PathVariable("id") Integer id) {
        // 使用mybatis-plus的removeById根据ID删除数据,返回boolean类型
        // 此处未处理对应的操作员角色对应表, 正常流程应是删除操作员后同时删除操作员角色对应关系
        if (adminService.removeById(id)) {
            return ResponseBean.success("删除操作员信息成功!");
        }
        return ResponseBean.error("删除操作员信息失败!");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public ResponseBean updateAdminRole(Integer adminId, Integer[] rids) {
        if (rids == null) {
            return ResponseBean.error("管理员必须拥有一个角色!");
        }
        return adminService.updateAdminRole(adminId, rids);
    }

}
