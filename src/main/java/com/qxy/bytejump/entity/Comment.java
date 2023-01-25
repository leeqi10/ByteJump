package com.qxy.bytejump.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String userName;

    private String content;

    private LocalDateTime createData;


}
