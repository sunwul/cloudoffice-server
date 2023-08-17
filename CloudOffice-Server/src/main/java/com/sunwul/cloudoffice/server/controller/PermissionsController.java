package com.sunwul.cloudoffice.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Menu;
import com.sunwul.cloudoffice.server.entity.MenuRole;
import com.sunwul.cloudoffice.server.entity.Role;
import com.sunwul.cloudoffice.server.service.MenuRoleService;
import com.sunwul.cloudoffice.server.service.MenuService;
import com.sunwul.cloudoffice.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*****
 * @author sunwul
 * @date 2021/3/24 22:29
 * @description 权限组
 */
@RestController
@RequestMapping("/system/basic/permissions")
public class PermissionsController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;

    // 单表的CRUD mybatis-plus都已经封装好了,不需要去写ServiceImpl和mapper数据库操作
    // 对于多表的操作, mybatis-plus也支持,但是要比我们自己写SQL复杂, 所以此工程的多表操作都不用mybatis-plus实现

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        // 使用mybatis-plus的list获取数据,返回list类型
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PutMapping("/role")
    public ResponseBean addRole(@RequestBody Role role) {
        System.out.println(role.toString());
        // startsWith() 用于检测字符串是否以指定的前缀开头
        if (!role.getName().startsWith("ROLE_")) {
            // 未以ROLE_开头,后端添加上  security的角色都是以ROLE_开头,要符合security的规则,否则security无法正常获取角色
            role.setName("ROLE_" + role.getName());
        }
        // 使用mybatis-plus的save保存数据,返回boolean类型
        if (roleService.save(role)) {
            return ResponseBean.success("添加角色成功!");
        }
        return ResponseBean.error("添加角色失败!");
    }

    // 不开放更新,避免更新出异常角色导致系统崩溃
//    @ApiOperation(value = "更新角色")
//    @PutMapping("/")
//    public ResponseBean updateRole(@RequestBody Role role) {
//        // 使用mybatis-plus的updateById根据ID更新数据,返回boolean类型
//        if (roleService.updateById(role)) {
//            return ResponseBean.success("更新角色成功!");
//        }
//        return ResponseBean.error("更新角色失败!");
//    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    // PathVariable 获取路径中的参数
    public ResponseBean deleteRole(@PathVariable("rid") Integer rid) {
        // 使用mybatis-plus的removeById根据ID删除数据,返回boolean类型
        // (待完善)删除前需要查询是否有admin为此角色,删除角色数据的同时删除角色菜单关联表
        if (roleService.removeById(rid)) {
            return ResponseBean.success("删除角色成功!");
        }
        return ResponseBean.error("删除角色失败!");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色ID查询菜单ID")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable("rid") Integer rid) {
        // 根据角色ID查询菜单ID本质上是查询MenuRole菜单角色对应表
        // 用rid去查MenuRole, 获取到数据后通过stream()流拿到查出来的菜单id, 然后转换成含菜单ID的数组
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());

    }

    @ApiOperation(value = "更新角色菜单")
    @PostMapping("/")
    public ResponseBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }
}
