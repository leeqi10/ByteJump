package com.qxy.bytejump.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.qxy.bytejump.entity.vo.VideoPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
