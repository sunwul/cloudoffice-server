package com.sunwul.cloudoffice.server.common.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunwul.cloudoffice.server.common.response.ResponseBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*****
 * @author sunwul
 * @date 2021/3/21 15:45
 * @description 当访问接口没有权限时, 自定义的返回结果
 * 拒绝访问统一处理
 */
@Component
public class RestfulAccessDeniedHander implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 设置响应信息
        ResponseBean responseBean = ResponseBean.error("权限不足,请联系管理员!");
        responseBean.setCode(403);
        // 设置响应对象
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 获取响应输出流
        PrintWriter out = response.getWriter();
        // 将响应信息写入响应输出流
        out.write(new ObjectMapper().writeValueAsString(responseBean));
        out.flush();
        // 关闭输出流
        out.close();
    }
}
