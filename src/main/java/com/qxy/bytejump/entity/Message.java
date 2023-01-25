package com.qxy.bytejump.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    public class Message implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String content;

      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;


}
