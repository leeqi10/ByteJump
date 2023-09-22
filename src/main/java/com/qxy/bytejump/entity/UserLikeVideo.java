package com.qxy.bytejump.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLikeVideo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String videoId;

    private String userid;

    private String isFavorite;
}
