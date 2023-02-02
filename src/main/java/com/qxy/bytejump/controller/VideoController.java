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
@Api(value = "video", tags = "视频")
@RequestMapping("//video")
public class VideoController {
    @ApiOperation(value = "查询视频", notes = "查询视频")
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        return "视频";
    }
}

