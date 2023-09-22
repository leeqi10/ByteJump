package com.qxy.bytejump.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qxy.bytejump.entity.vo.VideoPlus;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RePUserVideo {
    /**
     * 状态码
     */
    private Integer status_code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String status_msg;
    /**
     * 视频列表
     */
    private List<VideoPlus> video_list;
    /**
     * 下次视频时间戳
     */
    private String next_time;

    public RePUserVideo(Integer status_code, String status_msg, List<VideoPlus> video_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.video_list = video_list;
    }

    public RePUserVideo(Integer status_code, String status_msg, String next_time, List<VideoPlus> video_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.next_time = next_time;
        this.video_list = video_list;
    }
}
