package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Department;
import com.sunwul.cloudoffice.server.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sunwul
 * @since 2021-03-21
 * 部门
 */
@Api()
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public ResponseBean addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public ResponseBean deleteDepartment(@PathVariable("id") Integer depId) {
        return departmentService.deleteDepartment(depId);
    }

}
