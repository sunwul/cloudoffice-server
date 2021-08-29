package com.sunwul.cloudoffice.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunwul.cloudoffice.server.common.utils.AdminUtils;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import com.sunwul.cloudoffice.server.common.utils.JwtTokenUtil;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.entity.AdminRole;
import com.sunwul.cloudoffice.server.entity.Role;
import com.sunwul.cloudoffice.server.mapper.AdminMapper;
import com.sunwul.cloudoffice.server.mapper.AdminRoleMapper;
import com.sunwul.cloudoffice.server.mapper.RoleMapper;
import com.sunwul.cloudoffice.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunwul
 * @since 2021-03-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    // 密码加密
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注入jwt_token工具类
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminMapper adminMapper;

    // token前缀
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 登录之后返回token
     *
     * @param username 用户名
     * @param password 密码
     * @param request  客户端的请求 通过这个对象提供的方法,可以获得客户端请求的所有信息
     * @return token信息
     */
    @Override
    public ResponseBean login(String username, String password, String code, HttpServletRequest request) {
        // 从session中获取验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        // 判断用户输入验证码是否为空, 校验验证码是否正确
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return ResponseBean.error("验证码输入错误,请重新输入");
        }
        // 根据前端传过来的username获取对应的UserDetails
        // 在com.sunwul.cloudoffice.server.common.sercurity.SecurityConfig中, 我重写了UserDetailsService
        // 会根据我自定义的逻辑获取对应的UserDetails, 所以此处loadUserByUsername获取的是经过我自定义逻辑返回的UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 判断前端传过来的密码是否和UserDetails中的密码一致
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseBean.error("用户名或密码不正确!");
        }
        // 判断用户是否被禁用
        if (!userDetails.isEnabled()) {
            return ResponseBean.error("账号被禁用,请联系管理员");
        }
        // 更新security登录用户对象,将登录的用户对象放在security的全文中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 登录成功, 根据登录信息获取token令牌
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        // 放入token
        tokenMap.put("token", token);
        // 放入token前缀
        tokenMap.put("tokenHead", tokenHead);
        return ResponseBean.success("登录成功!", tokenMap);
    }

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    @Override
    public Admin getAdminByUserName(String username) {
        // 查询单条数据, 使用mybatis-plus的selectOne
        // 参数为QueryWrapper<T> 然后放入对象,同时匹配用户名与是否启用两个字段
        /**
         * 修改, 不匹配是否启用 .eq("enabled", true)
         * @see com.sunwul.cloudoffice.server.common.sercurity.SecurityConfig line125-138
         * 若是匹配是否启用字段,查询数据库时被禁用的账户就无法被查询到
         * 此时会导致SecurityConfig重写UserDetailsService时因为无法获取到对应用户而抛出UsernameNotFoundException
         * UsernameNotFoundException我定义的异常消息是用户不存在,不符合实际, 所以此处去掉对是否启用字段的匹配
         * @see AdminServiceImpl line92 在用户登录时判断用户是否启用, 返回对应消息
         */
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId 用户id
     * @return 角色列表
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有操作员
     *
     * @param keywords 关键字
     * @return 操作员列表
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        // 获取当前登录的用户信息
//        Admin admin = (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 从工具类中获取用户信息
        // 不查当前登录用户(自己)的信息, 当前登录的用户信息在个人中心
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
    }

    /**
     * 更新操作员角色
     *
     * @param adminId 操作员id
     * @param rids    角色id数组
     * @return 更新结果
     * * @Transactional  事务  此方法涉及到两个数据库操作(delete/insert),所以添加此注解
     */
    @Override
    @Transactional
    public ResponseBean updateAdminRole(Integer adminId, Integer[] rids) {
        // 先根据传入的adminId去删除操作员角色对应表中的数据,即先删除此id下的所有角色
        // 使用mybatis-plus的delete根据传入参数删除数据,返回int类型
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("admin_id", adminId));
        // 再插入adminId所对应的新的角色id数组
        Integer result = adminRoleMapper.insertAdminRole(adminId, rids);
        if (result == rids.length) {
            return ResponseBean.success("更新操作员角色成功!");
        }
        return ResponseBean.error("更新操作员角色失败!");
    }

    /**
     * 更新当前操作员密码
     *
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @param adminId 操作员id
     * @return 操作结果
     */
    @Override
    public ResponseBean updateAdminPassword(String oldPass, String newPass, Integer adminId) {
        // 先从数据库中获取操作员信息   正常情况不会出现查询为空, 这里加上是为了测试
        Admin admin = adminMapper.selectById(adminId);
        if (admin != null) {
            // 获取security的BCryptPasswordEncoder, 虽然前端输入的是明文,但项目中用到了security, 它会加密输入信息
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            // 比较旧密码与数据库中存储的密码
            if (encoder.matches(oldPass, admin.getPassword())) {
                // 旧密码与数据库中存储的密码匹配通过后, 写入新密码(要加密)
                admin.setPassword(encoder.encode(newPass));
                // 更新数据库中的信息
                int result = adminMapper.updateById(admin);
                if (result == 1) {
                    return ResponseBean.success("更新当前用户密码成功!");
                    // 更新成功后将用户踢下线并且要求重新登录由前端做处理
                }
            }
            return ResponseBean.error("旧密码不正确,更新失败!");
        }
        return ResponseBean.error("未获取到用户信息,请查看用户ID是否正确!");
    }

    /**
     * 更新用户头像
     *
     * @param url            文件完整路径
     * @param id             用户id
     * @param authentication Authentication
     * @return 更新结果
     */
    @Override
    public ResponseBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
        // 获取特定ID的用户
        Admin admin = adminMapper.selectById(id);
        // 设置用户头像
        admin.setUser_face(url);
        // 更新用户信息
        int result = adminMapper.updateById(admin);
        if (result == 1) {
            // 更新成功后去更新全局对象
            Admin principal = (Admin) authentication.getPrincipal();
            // 更新全局对象中的用户头像
            principal.setUser_face(url);
            // 更新security全局上下文
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(
                            admin,
                            null,
                            authentication.getAuthorities()));
            return ResponseBean.success("更新用户头像成功!", url);
        }
        return ResponseBean.error("更新用户头像失败!");
    }
}
