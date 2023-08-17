package com.sunwul.cloudoffice.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Department;
import com.sunwul.cloudoffice.server.mapper.DepartmentMapper;
import com.sunwul.cloudoffice.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     *
     * @return 所有部门
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     *
     * @param department 要添加的部门信息
     * @return 操作结果
     */
    @Override
    public ResponseBean addDepartment(Department department) {
        // 默认启用
        department.setEnabled("1");
        departmentMapper.addDep(department);
        // 当result有数据时
        if (department.getResult() == 1) {
            return ResponseBean.success("添加部门信息成功!", department);
        }
        return ResponseBean.error("添加部门信息失败!");
    }

    /**
     * 删除部门
     *
     * @param depId 部门id
     * @return 操作结果
     */
    @Override
    public ResponseBean deleteDepartment(Integer depId) {
        Department department = new Department();
        // 写入要删除的部门的id
        department.setId(depId);
        departmentMapper.deleteDepartment(department);
        if (department.getResult() == -2) {
            return ResponseBean.error("该部门下还有子部门,删除失败!");
        }
        if (department.getResult() == -1) {
            return ResponseBean.error("该部门下还有员工,删除失败!");
        }
        if (department.getResult() == 1) {
            return ResponseBean.success("删除成功!");
        }
        return ResponseBean.error("删除失败!");
    }
}
