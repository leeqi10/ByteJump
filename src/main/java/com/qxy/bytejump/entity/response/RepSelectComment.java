package com.qxy.bytejump.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qxy.bytejump.entity.vo.SetComment;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepSelectComment {
    /**
     * 状态码
     */
    private Integer status_code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String status_msg;
    /**
     * 评论列表信息
     */
    private List<SetComment> comment_list;

    public RepSelectComment(Integer status_code, String status_msg, List<SetComment> comment_list) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.comment_list = comment_list;
    }
}
