package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunwul.cloudoffice.server.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface EmployeeMapper extends BaseMapper<Employee> {


    /**
     * 获取所有员工(分页)
     *
     * @param page           分页参数
     * @param employee       员工信息
     * @param beginDateScope 开始日期范围
     * @return ResponsePageBean 分页统一返回对象
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 查询员工(当传入ID时查询此员工的信息,当未传入ID时查询所有员工信息)
     *
     * @param id 员工ID
     */
    List<Employee> getEmployee(Integer id);
}
