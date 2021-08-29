package com.sunwul.cloudoffice.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.MenuRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface MenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid  角色ID
     * @param mids 菜单ID数组
     * @return 更新结果
     */
    ResponseBean updateMenuRole(Integer rid, Integer[] mids);
}
