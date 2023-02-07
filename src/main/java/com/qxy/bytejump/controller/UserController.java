package com.qxy.bytejump.controller;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.response.UserLR;
import com.qxy.bytejump.entity.vo.ResponseUser;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@RestController
@Api(value = "user", tags = "用户")
@RequestMapping("//douyin")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/user/register")
    public UserLR userRegister(User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/user/login")
    public UserLR userLogin(User user) {
        return userService.login(user);
    }

    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/user/")
    public ResponseUser user(@Param("user_id") String user_id, @Param("token") String token) {
        return userService.getUser(user_id,token);
    }
}

