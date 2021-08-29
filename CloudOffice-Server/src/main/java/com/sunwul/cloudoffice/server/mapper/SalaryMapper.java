package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunwul.cloudoffice.server.entity.Employee;
import com.sunwul.cloudoffice.server.entity.Salary;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    /**
     * 获取所有员工账套(分页)
     *
     * @param page page
     * @return ResponsePageBean
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
