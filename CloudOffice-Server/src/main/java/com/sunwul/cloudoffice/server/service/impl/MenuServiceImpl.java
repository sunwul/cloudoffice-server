package com.sunwul.cloudoffice.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunwul.cloudoffice.server.common.utils.AdminUtils;
import com.sunwul.cloudoffice.server.entity.Menu;
import com.sunwul.cloudoffice.server.mapper.MenuMapper;
import com.sunwul.cloudoffice.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单列表
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        // 获取AdminId, 从security全局上下文中获取, 在用户成功登录后, 用户对象会存在security全局上下文的Principal中
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal()
//        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        // 从工具类的封装方法中获取用户id
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        // 从Redis中获取菜单数据
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("CloudOffice-menu_" + adminId);
        // 判断从Redis中获取的是否为空
        if (CollectionUtils.isEmpty(menus)) {
            // Redis为空时,从数据库获取数据
            menus = menuMapper.getMenusByAdminId(adminId);
            // 将从数据库获取到的数据放入Redis, 同时设置缓存时间为5分钟,5分钟后失效,需要重新从数据库中获取数据
            valueOperations.set("CloudOffice-menu_" + adminId, menus, 60*5,TimeUnit.SECONDS);
        }
        return menus;
        // 测试时为了看到查询的即时效果,建议把从redis中获取菜单数据部分注释掉
//        return menuMapper.getMenusByAdminId(adminId);
    }

    /**
     * 获取菜单列表与对应的角色  -- 权限
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单列表 -- 权限
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
