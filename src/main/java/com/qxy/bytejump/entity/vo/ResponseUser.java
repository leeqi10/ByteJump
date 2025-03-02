package com.qxy.bytejump.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ResponseUser<T> {
    /**
     * 状态码
     */
    private Integer status_code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String status_msg;
    /**
     * 查询到的结果数据，
     */
    private T user;

    public ResponseUser(Integer status_code, String status_msg) {
        this.status_code = status_code;
        this.status_msg = status_msg;
    }

    public ResponseUser(Integer status_code, T user) {
        this.status_code = status_code;
        this.user = user;
    }

    public ResponseUser(Integer status_code, String status_msg, T user) {
        this.status_code = status_code;
        this.status_msg = status_msg;
        this.user = user;
    }
}
