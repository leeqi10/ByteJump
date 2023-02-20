package com.qxy.bytejump.controller;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
@RequestMapping("//douyin")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @ApiOperation(value = "查询视频", notes = "查询视频")
    @GetMapping("/feed")
    @ResponseBody
    public String getFeed(@Param("latest_time") String latest_time,@Param("token")  String token) {
        System.out.println(latest_time);
        System.out.println(token);
        return "{\n" +
                "    \"status_code\": 0,\n" +
                "    \"status_msg\": \"string\",\n" +
                "    \"next_time\": 0,\n" +
                "    \"video_list\": [\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"author\": {\n" +
                "                \"id\": 0,\n" +
                "                \"name\": \"string\",\n" +
                "                \"follow_count\": 0,\n" +
                "                \"follower_count\": 0,\n" +
                "                \"is_follow\": true\n" +
                "            },\n" +
                "            \"play_url\": \"string\",\n" +
                "            \"cover_url\": \"string\",\n" +
                "            \"favorite_count\": 0,\n" +
                "            \"comment_count\": 0,\n" +
                "            \"is_favorite\": true,\n" +
                "            \"title\": \"string\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
    @ApiOperation(value = "投稿视频", notes = "投稿视频")
    @PostMapping("/publish/action/")
    @ResponseBody
    public Result setUpload(@Param("data") MultipartFile data, @Param("token")String token, @Param("title")String title){
        return videoService.Upload(data,token,title);

    }
}

