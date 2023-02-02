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
@RequestMapping("//user")
public class UserController {
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        return "用户";
    }
}

