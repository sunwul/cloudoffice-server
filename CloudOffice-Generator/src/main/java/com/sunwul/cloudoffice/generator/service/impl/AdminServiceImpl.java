package com.sunwul.cloudoffice.generator.service.impl;

import com.sunwul.cloudoffice.generator.entity.Admin;
import com.sunwul.cloudoffice.generator.mapper.AdminMapper;
import com.sunwul.cloudoffice.generator.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
