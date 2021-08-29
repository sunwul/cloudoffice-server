package com.sunwul.cloudoffice.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.MenuRole;
import com.sunwul.cloudoffice.server.mapper.MenuRoleMapper;
import com.sunwul.cloudoffice.server.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     *
     * @param rid  角色ID
     * @param mids 菜单ID数组
     * @return 更新结果
     * * @Transactional  事务  此方法涉及到两个数据库操作(delete/insert),所以添加此注解
     */
    @Override
    @Transactional
    public ResponseBean updateMenuRole(Integer rid, Integer[] mids) {
        // 删除此角色下的所有菜单   使用mybatis-plus的delete根据传入参数删除数据,返回int类型
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        // 判断菜单ID数组是否为空
        if (mids == null || mids.length == 0) {
            return ResponseBean.success("更新角色菜单成功!");
        }
        // 批量插入新的角色菜单对应关系
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        if (result == mids.length) {
            return ResponseBean.success("更新角色菜单成功!");
        }
        return ResponseBean.error("更新角色菜单失败!");
    }
}
