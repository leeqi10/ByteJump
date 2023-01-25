package com.qxy.bytejump.entity;

import java.io.Serializable;
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

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;

    private Integer followCount;

    private Integer followerCount;

    private String isFollow;

    private String password;


  public String getUsername() {
    return name;
  }
}
