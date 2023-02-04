package com.qxy.bytejump.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Controller
@Api(value = "user", tags = "用户")
@RequestMapping("//douyin")
public class UserController {
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @GetMapping("/user/register")
    public String userRegister( Long id) {
        return "用户注册";
    }
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @GetMapping("/user/login")
    public String userLogin(Long id) {
        return "用户登录";
    }
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/user/")
    public String user( Long id) {
        return "用户信息";
    }
}

