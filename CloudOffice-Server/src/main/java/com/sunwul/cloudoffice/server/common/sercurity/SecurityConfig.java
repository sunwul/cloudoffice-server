package com.sunwul.cloudoffice.server.common.sercurity;

import com.sunwul.cloudoffice.server.common.sercurity.Filter.CustomFilter;
import com.sunwul.cloudoffice.server.common.sercurity.Filter.CustomUrlDecisionManager;
import com.sunwul.cloudoffice.server.entity.Admin;
import com.sunwul.cloudoffice.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*****
 * @author sunwul
 * @date 2021/3/21 14:29
 * @description Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;

    // 自定义token失效或用户未登录返回结果
    @Autowired
    private RestAuthorizationEntryPoint authorizationEntryPoint;

    // 自定义无访问权限返回结果
    @Autowired
    private RestfulAccessDeniedHander accessDeniedHander;

    // 自定义权限控制 - 根据请求URL分析请求所需的角色
    @Autowired
    private CustomFilter customFilter;

    // 自定义权限控制 - 判断用户角色
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;


    /**
     * 重写config,实现自定义逻辑
     *
     * @param auth 认证管理器
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于 UserDetailsService 的管理器
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf保护,默认开启  使用JWT,不需要csrf
        http.csrf().disable()
                // 使用token,不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 显式自定义访问控制
        http.authorizeRequests()
                // 指定不需要认证的页面  在configure(WebSecurity web)中配置了这里就不需要再配置  line-106
//                .antMatchers("/login", "/logout")
//                .permitAll()
                // 其它任何请求都需要认证
                .anyRequest().authenticated()
                // 动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        // 添加自定义权限控制
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilter);
                        return object;
                    }
                });
        // 不使用缓存
        http.headers().cacheControl().disable();
        // 添加JWT登录授权过滤器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHander)
                .authenticationEntryPoint(authorizationEntryPoint);
        // 设置用户注销
        http.logout().logoutUrl("/logout").permitAll();
    }

    /**
     * 配置不走拦截链的请求路径
     *
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) {
        // 设置放行路径  登录,登出
        web.ignoring().antMatchers("/login", "/logout",
                // 放行静态资源
                "/css/**", "/js/**", "index.html", "favicon.ico",
                // 放行swagger的资源
                "/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**",
                // 放行验证码获取
                "/captcha",
                // 放行WebSocket请求
                "/ws/**");
    }

    /**
     * 重写UserDetailsService
     *
     * @return UserDetailsService
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // 使用自定义的根据用户名获取用户方法,得到对应的UserDetails
        return username -> {
            // 从数据库中查找对象
            Admin admin = adminService.getAdminByUserName(username);
            if (admin != null) {
                // 写入用户所拥有的角色
                admin.setRoles(adminService.getRoles(admin.getId()));
                return admin;
            }
            // 此处抛出了异常, 若是全局异常捕获也捕获此异常, 那么全局异常捕获的消息优先级大于此处, 即会显示全局异常的消息
            throw new UsernameNotFoundException("throw - 此用户不存在!");
//            return null;
        };
    }

    /**
     * 使用自定义的jwt过滤器
     *
     * @return jwt过滤器
     */
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter() {
        return new JwtAuthencationTokenFilter();
    }

    /**
     * 设置security的加密方式
     * 无密码加密: return NoOpPasswordEncoder.getInstance();
     *
     * @return 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
