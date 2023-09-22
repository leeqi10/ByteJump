package com.qxy.bytejump.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private Integer followCount;

    private Integer followerCount;

    private String isFollow;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
