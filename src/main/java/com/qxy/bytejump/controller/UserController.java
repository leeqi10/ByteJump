package com.qxy.bytejump.controller;

import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.response.UserLR;
import com.qxy.bytejump.entity.vo.ResponseUser;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
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
    public ResponseUser user(String user_id, String token) {
        return userService.getUser(user_id, token);
    }

    @ApiOperation(value = "赞操作", notes = "赞操作")
    @PostMapping("/favorite/action/")
    @ResponseBody
    public Result userLikeVideo(String token, String video_id, String action_type) {
        return userService.userIsLike(token, video_id, action_type);
    }

    @ApiOperation(value = "用户喜欢列表", notes = "用户喜欢列表")
    @GetMapping("/favorite/list/")
    @ResponseBody
    public RePUserVideo SelectAllLikeVideo(String token, String user_id) {
        return userService.selectAllUserLike(token, user_id);
    }

}

