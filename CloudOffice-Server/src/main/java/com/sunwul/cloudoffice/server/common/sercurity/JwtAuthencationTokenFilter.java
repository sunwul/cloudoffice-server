package com.sunwul.cloudoffice.server.common.sercurity;

import com.sunwul.cloudoffice.server.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*****
 * @author sunwul
 * @date 2021/3/21 15:04
 * @description JWT登录授权过滤器
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader; // token在请求头中的位置
    @Value("${jwt.tokenHead}")
    private String tokenHead; // token前缀

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 重写拦截
     *
     * @param request 请求
     * @param response 响应
     * @param filterChain 过滤
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取前端需要传过来的Authorization
        String authHeader = request.getHeader(tokenHeader);
        // 如果前端没有传递tokenHeader,或者tokenHeader不是以tokenHead开头
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            // 截取token 去掉前缀
            String authToken = authHeader.substring(tokenHead.length());
            // 从token中获取用户名
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            // 能从token中拿到用户名, 但是从security的全局上下文中拿不到对应对象 (未登录)
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 验证token是否有效
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    // 失效, 重新设置用户对象
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 放入security全局上下文中
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
