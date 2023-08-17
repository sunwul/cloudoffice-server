package com.sunwul.cloudoffice.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*****
 * @author sunwul
 * @date 2021/3/21 16:18
 * @Description 测试controller
 */
//@Api(value = "TestController", tags = "TestController")
@RestController
public class TestController {

    @ApiOperation(value = "联通测试", notes = "测试系统是否基本正常")
    @GetMapping("/test")
    public String test() {
        return "测试";
    }

    @GetMapping("/employee/basic/hello")
    public String test2() {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String test3() {
        return "/employee/advanced/hello";
    }


}
