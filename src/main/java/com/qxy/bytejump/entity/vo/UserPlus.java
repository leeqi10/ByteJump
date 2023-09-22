package com.qxy.bytejump.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserPlus {
    private Long id;

    private String name;

    private Integer follow_count;

    private Integer follower_count;

    private String is_follow;

    private String avatar;

    private String background_image;

    private String signature;

    private String total_favorite;

    private Integer work_count;

    private Integer favorite_count;
}
