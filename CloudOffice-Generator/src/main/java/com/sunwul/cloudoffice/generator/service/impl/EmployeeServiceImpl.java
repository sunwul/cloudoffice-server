package com.sunwul.cloudoffice.generator.service.impl;

import com.sunwul.cloudoffice.generator.entity.Employee;
import com.sunwul.cloudoffice.generator.mapper.EmployeeMapper;
import com.sunwul.cloudoffice.generator.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
