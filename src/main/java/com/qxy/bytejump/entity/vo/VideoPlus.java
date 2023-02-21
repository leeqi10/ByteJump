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
    private String playUrl;

    /**
     * 视频封面地址
     */
    private String coverUrl;

    /**
     * 视频点赞总数
     */
    private Integer favoriteCount;

    /**
     * 视频评论总数
     */
    private Integer commentCount;

    /**
     * 点赞或者为点赞
     */
    private String isFavorite;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频作者的名字
     */
    private String userName;

    private User author;


    public VideoPlus(Integer id, String playUrl, String coverUrl, Integer favoriteCount, Integer commentCount, String idFavorite, String title, User author) {
        this.id = id;
        this.playUrl = playUrl;
        this.coverUrl = coverUrl;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
        this.isFavorite = isFavorite;
        this.title = title;
        this.author = author;
    }
    public VideoPlus(Integer id, String playUrl, String coverUrl, Integer favoriteCount, Integer commentCount, String idFavorite, String title, String userName) {
        this.id = id;
        this.playUrl = playUrl;
        this.coverUrl = coverUrl;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
        this.isFavorite = isFavorite;
        this.title = title;
        this.userName = userName;
    }

    public VideoPlus(Integer id, String playUrl, String coverUrl, Integer favoriteCount, Integer commentCount, String idFavorite, String title) {
        this.id = id;
        this.playUrl = playUrl;
        this.coverUrl = coverUrl;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
        this.isFavorite = isFavorite;
        this.title = title;
    }

}
