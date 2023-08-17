package com.sunwul.cloudoffice.server.common.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*****
 * @author sunwul
 * @date 2021/3/21 15:37
 * @description 当未登录, 或者token失效访问接口时, 自定义的返回结果
 * 未认证或认证失效统一处理 , 认证入口点AuthenticationEntryPoint
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 设置响应信息
        ResponseBean responseBean = ResponseBean.error("未登录,请登录后再访问!");
        responseBean.setCode(401);
        // 设置响应对象
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 获取响应输出流
        PrintWriter out = response.getWriter();
        // 将响应信息转换为JSON,写入响应输出流
        out.write(new ObjectMapper().writeValueAsString(responseBean));
        out.flush();
        // 关闭输出流
        out.close();
    }
}
