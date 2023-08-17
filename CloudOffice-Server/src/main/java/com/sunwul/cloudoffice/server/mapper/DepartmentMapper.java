package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunwul.cloudoffice.server.entity.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     *
     * @param parent_id 父id
     * @return 所有部门
     */
    List<Department> getAllDepartments(Integer parent_id);

    /**
     * 添加部门
     *
     * @param department 要添加的部门信息
     * @return 操作结果
     */
    void addDep(Department department);

    /**
     * 删除部门
     *
     * @param department 要删除的部门信息
     * @return 操作结果
     */
    void deleteDepartment(Department department);
}
