package com.qxy.bytejump.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SetComment {
    private Integer id;

    private UserPlus user;

    private String content;

    private String createData;

    public SetComment(Integer id, UserPlus user, String content, String createData) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createData = createData;
    }

    public SetComment() {
    }
}
