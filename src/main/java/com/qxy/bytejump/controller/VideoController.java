package com.qxy.bytejump.controller;
import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
                "            \"id\": 18,\n" +
                "            \"author\": {\n" +
                "                \"id\": 11,\n" +
                "                \"name\": \"tqq\",\n" +
                "                \"follow_count\": 0,\n" +
                "                \"follower_count\": 0,\n" +
                "                \"is_follow\": true\n" +
                "            },\n" +
                "            \"play_url\": \"http://192.168.1.184:8084/video/11/1234.mp4\",\n" +
                "            \"cover_url\": \"http://192.168.1.184:8084/Cover/11/1234.jpeg\",\n" +
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
    @ApiOperation(value = "用户视频列表", notes = "用户视频列表")
    @GetMapping("/publish/list/")
    @ResponseBody
    public RePUserVideo SelectAllVideo(@Param("token") String token, @Param("user_id") String user_id){
        return videoService.selectAllUserVideo(token,user_id);
    }
}

