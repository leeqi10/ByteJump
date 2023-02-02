package com.qxy.bytejump.controller;


import com.qxy.bytejump.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Controller
@RequestMapping("//comment")
@Api(value = "comment", tags = "评论")
public class CommentController {
    @ApiOperation(value = "查询评论", notes = "查询评论")
    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        return "评论";
    }

}

