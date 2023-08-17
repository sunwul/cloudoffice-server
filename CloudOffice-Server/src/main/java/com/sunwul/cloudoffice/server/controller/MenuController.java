package com.sunwul.cloudoffice.server.controller;


import com.sunwul.cloudoffice.server.entity.Menu;
import com.sunwul.cloudoffice.server.service.AdminService;
import com.sunwul.cloudoffice.server.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 * 菜单
 */
@RestController
//@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "根据当前登录用户ID查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        // 通常在用户登录后,在token有效的前提下,用户的相关信息都是在后端直接获取的, 而不是前端传进来
        return menuService.getMenusByAdminId();
    }

    // 当对数据库中的菜单修改或删除时, 同时应该把Redis中的菜单数据清空,
    // 这样在用户下次登录或查询时,菜单数据才会是最新的, 否则用户获取到的菜单数据会一直是老数据

}
