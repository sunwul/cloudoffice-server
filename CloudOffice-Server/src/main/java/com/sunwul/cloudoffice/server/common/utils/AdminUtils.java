package com.sunwul.cloudoffice.server.common.utils;

import com.sunwul.cloudoffice.server.entity.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/*****
 * @author sunwul
 * @date 2021/3/26 19:56
 * @description 用户工具类
 */
public class AdminUtils {

    /**
     * 获取当前登录的用户
     *
     * @return 当前登录用户
     */
    public static Admin getCurrentAdmin() {
        // 从security全局上下文中获取当前登录用户
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
