package com.qxy.bytejump.controller;

import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Controller
@Api(value = "video", tags = "视频")
@RequestMapping("//douyin")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "查询视频", notes = "查询视频")
    @GetMapping("/feed")
    @ResponseBody
    public RePUserVideo getFeed(String latest_time, String token) {
        System.out.println(latest_time);
        System.out.println(token);
        return videoService.selectAllVideo(latest_time, token);
    }

    @ApiOperation(value = "投稿视频", notes = "投稿视频")
    @PostMapping("/publish/action/")
    @ResponseBody
    public Result setUpload(MultipartFile data, String token, String title) {
        return videoService.Upload(data, token, title);
    }

    @ApiOperation(value = "用户视频列表", notes = "用户视频列表")
    @GetMapping("/publish/list/")
    @ResponseBody
    public RePUserVideo SelectAllVideo(String token, String user_id) {
        return videoService.selectAllUserVideo(token, user_id);
    }

}

