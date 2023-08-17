package com.sunwul.cloudoffice.server.common.sercurity.Filter;

import com.sunwul.cloudoffice.server.entity.Menu;
import com.sunwul.cloudoffice.server.entity.Role;
import com.sunwul.cloudoffice.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/23 20:26
 * @description 权限控制 - 根据请求URL分析请求所需的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    private AntPathMatcher getAntPathMatcher() {
        return new AntPathMatcher();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求的URL
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取所有菜单与角色对应结果(结果包含菜单,菜单所对应的角色)
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu : menus) {
            // 判断菜单URL的值是否为null
            if (menu.getUrl() == null || menu.getUrl().equals("null")) {
                // 当菜单URL的值为null时,为它设置一个空字符,否则match匹配会因为null报空指针异常
                menu.setUrl("");
            }
            // 判断请求URL与菜单URL是否匹配
            if (getAntPathMatcher().match(menu.getUrl(), requestUrl)) {
                // stream() --> JDK8特性
                // 获取所有匹配的角色 (获取Role中的Name属性,放入List<String>中,然后toArray转换成String[])
                String[] strs = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                // 将匹配到的角色全部放入security
                return SecurityConfig.createList(strs);
            }
        }
        // 未匹配的URL登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
