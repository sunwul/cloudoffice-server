package com.sunwul.cloudoffice.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunwul.cloudoffice.server.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户ID查询菜单列表
     *
     * @return 菜单列表
     */
    List<Menu> getMenusByAdminId();

    /**
     * 获取菜单列表与对应的角色
     *
     * @return 菜单列表
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单列表
     *
     * @return 菜单列表
     */
    List<Menu> getAllMenus();
}
