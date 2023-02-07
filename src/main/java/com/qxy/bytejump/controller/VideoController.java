package com.qxy.bytejump.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ApiOperation(value = "查询视频", notes = "查询视频")
    @GetMapping("/user")
    @ResponseBody
    public String get(@Param("user_id") String user_id,@Param("token")  String token) {
        System.out.println(user_id);
        System.out.println(token);
        return "{\n" +
                "    \"status_code\": 0,\n" +
                "    \"status_msg\": \"string\",\n" +
                "    \"user\": {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"tqq\",\n" +
                "        \"follow_count\": 0,\n" +
                "        \"follower_count\": 0,\n" +
                "        \"is_follow\": true\n" +
                "    }\n" +
                "}";
    }
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
}

