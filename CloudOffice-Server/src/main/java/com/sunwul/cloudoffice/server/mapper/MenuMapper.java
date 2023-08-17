package com.sunwul.cloudoffice.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunwul.cloudoffice.server.entity.Menu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户ID查询菜单列表
     *
     * @param id 用户ID
     * @return 菜单列表
     */
    List<Menu> getMenusByAdminId(Integer id);

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
