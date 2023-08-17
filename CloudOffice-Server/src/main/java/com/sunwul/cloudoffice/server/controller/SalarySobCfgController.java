package com.sunwul.cloudoffice.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.response.ResponsePageBean;
import com.sunwul.cloudoffice.server.entity.Employee;
import com.sunwul.cloudoffice.server.entity.Salary;
import com.sunwul.cloudoffice.server.service.EmployeeService;
import com.sunwul.cloudoffice.server.service.SalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/28 18:20
 * @description 员工账套
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工账套(分页)")
    @GetMapping("/")
    public ResponsePageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeWithSalary(currentPage, size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public ResponseBean updateEmployeeSalary(Integer eid, Integer sid) {
        // 更新id为eid的员工, 更新员工工资账套ID为sid
        if (employeeService.update(new UpdateWrapper<Employee>().set("salary_id", sid).eq("id", eid))) {
            return ResponseBean.success("更新员工账套成功!");
        }
        return ResponseBean.error("更新员工账套失败!");
    }
}
