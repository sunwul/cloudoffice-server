package com.sunwul.cloudoffice.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Department;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @return 所有部门
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     *
     * @param department 要添加的部门信息
     * @return 操作结果
     */
    ResponseBean addDepartment(Department department);

    /**
     * 删除部门
     *
     * @param depId 部门id
     * @return 操作结果
     */
    ResponseBean deleteDepartment(Integer depId);
}
