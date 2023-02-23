package com.qxy.bytejump.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qxy.bytejump.entity.vo.SetComment;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class RePSetComment {
    /**
     * 状态码
     */
    private Integer status_code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String status_msg;

    private SetComment comment;

    public RePSetComment(Integer status_code, String status_msg, SetComment comment) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.comment = comment;
    }
}
