package com.qxy.bytejump.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qxy.bytejump.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class VideoPlus {
    /**
     * 视频唯一表示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频播放地址
     */
    private String play_url;

    /**
     * 视频封面地址
     */
    private String cover_url;

    /**
     * 视频点赞总数
     */
    private Integer favorite_count;

    /**
     * 视频评论总数
     */
    private Integer comment_count;

    /**
     * 点赞或者为点赞
     */
    private String is_favorite;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频作者的名字
     */
    private String userName;

    private User author;

    public VideoPlus(Integer id, String play_url, String cover_url, Integer favorite_count, Integer comment_count, String is_favorite, String title, String userName, User author) {
        this.id = id;
        this.play_url = play_url;
        this.cover_url = cover_url;
        this.favorite_count = favorite_count;
        this.comment_count = comment_count;
        this.is_favorite = is_favorite;
        this.title = title;
        this.userName = userName;
        this.author = author;
    }

    public VideoPlus(Integer id, String play_url, String cover_url, Integer favorite_count, Integer comment_count, String is_favorite, String title, String userName) {
        this.id = id;
        this.play_url = play_url;
        this.cover_url = cover_url;
        this.favorite_count = favorite_count;
        this.comment_count = comment_count;
        this.is_favorite = is_favorite;
        this.title = title;
        this.userName = userName;
    }
}
