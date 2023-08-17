package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Salary;
import com.sunwul.cloudoffice.server.service.SalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sunwul
 * @since 2021-03-21
 * 工资账套
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/")
    public ResponseBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return ResponseBean.success("添加工资账套成功!");
        }
        return ResponseBean.error("添加工资账套失败!");
    }

    @ApiOperation(value = "更新工资账套")
    @PutMapping("/")
    public ResponseBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return ResponseBean.success("更新工资账套成功!");
        }
        return ResponseBean.success("更新工资账套失败!");
    }

    @ApiOperation(value = "删除工资账套")
    @DeleteMapping("/{id}")
    public ResponseBean deleteSalary(@PathVariable("id") Integer id) {
        if (salaryService.removeById(id)) {
            return ResponseBean.success("删除工资账套成功!");
        }
        return ResponseBean.error("删除工资账套失败!");
    }
}
