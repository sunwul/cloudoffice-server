package com.sunwul.cloudoffice.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.response.ResponsePageBean;
import com.sunwul.cloudoffice.server.entity.Employee;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage    当前页
     * @param size           数据数量
     * @param employee       员工信息
     * @param beginDateScope 开始日期范围
     * @return ResponsePageBean 分页统一返回对象
     */
    ResponsePageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取最新工号
     *
     * @return 操作结果
     */
    ResponseBean getMaxWorkId();

    /**
     * 添加员工
     *
     * @param employee 员工信息
     * @return 操作结果
     */
    ResponseBean addEmp(Employee employee);

    /**
     * 查询员工(当传入ID时查询此员工的信息,当未传入ID时查询所有员工信息)
     *
     * @param id 员工ID
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 获取所有员工账套(分页)
     *
     * @param currentPage 当前页
     * @param size        每页数量
     * @return ResponsePageBean
     */
    ResponsePageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
