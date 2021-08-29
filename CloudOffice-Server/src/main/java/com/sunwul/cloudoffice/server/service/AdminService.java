package com.sunwul.cloudoffice.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username 用户名
     * @param password 密码
     * @param request  客户端的请求 通过这个对象提供的方法,可以获得客户端请求的所有信息
     * @return token信息
     */
    ResponseBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId 用户id
     * @return 角色列表
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有操作员
     *
     * @param keywords 关键字
     * @return 操作员列表
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     *
     * @param adminId 操作员id
     * @param rids    角色id数组
     * @return 操作结果
     */
    ResponseBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新当前操作员密码
     *
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @param adminId 操作员id
     * @return 操作结果
     */
    ResponseBean updateAdminPassword(String oldPass, String newPass, Integer adminId);

    /**
     * 更新用户头像
     *
     * @param url            文件完整路径
     * @param id             用户id
     * @param authentication Authentication
     * @return 更新结果
     */
    ResponseBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
