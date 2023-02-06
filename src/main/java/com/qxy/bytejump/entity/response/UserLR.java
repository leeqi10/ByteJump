package com.qxy.bytejump.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLR {
    /**
     * 状态码
     */
    private Integer status_code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String status_msg;
    /**
     * 用户id
     */

    private Integer user_id;
    /**
     * 用户的token
     */
    private String token;

    public UserLR(Integer status_code, String status_msg) {
        this.status_code = status_code;
        this.status_msg = status_msg;
    }

    public UserLR(Integer status_code, String status_msg, Integer user_id, String token) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.user_id = user_id;
        this.token = token;
    }
}
