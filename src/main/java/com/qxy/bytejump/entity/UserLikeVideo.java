package com.qxy.bytejump.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserLikeVideo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String video_id;

    private String user_id;

    private String  is_favorite;
}
