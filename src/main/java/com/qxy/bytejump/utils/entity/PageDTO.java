package com.qxy.bytejump.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changzer
 * @date 2022/12/5
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    protected Long current;
    protected Long pageSize;
}
