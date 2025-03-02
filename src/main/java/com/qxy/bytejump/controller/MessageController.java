package com.qxy.bytejump.controller;

import com.qxy.bytejump.shedule.RedisSchedule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Controller
@Api(value = "message", tags = "信息")
@RequestMapping("//message")
public class MessageController {
    @Autowired
    private RedisSchedule redisSchedule;
    @ApiOperation(value = "查询信息", notes = "查询信息")
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        redisSchedule.redisCacheFlush();
        return "信息";
    }
}

