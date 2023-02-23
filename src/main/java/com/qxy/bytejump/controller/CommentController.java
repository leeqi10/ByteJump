package com.qxy.bytejump.controller;
import com.qxy.bytejump.entity.response.RePSetComment;
import com.qxy.bytejump.entity.response.RepSelectComment;
import com.qxy.bytejump.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Controller
@RequestMapping("//douyin/comment")
@Api(value = "comment", tags = "评论")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "查询评论", notes = "查询评论")
    @GetMapping("/list/")
    @ResponseBody
    public RepSelectComment selectAllComment(@Param("token") String token, @Param("video_id") String video_id) {
        return commentService.selectAllComment(token,video_id);
    }

    @ApiOperation(value = "评论操作", notes = "评论操作")
    @PostMapping("/action/")
    @ResponseBody
    public RePSetComment setComment(@Param("token") String token, @Param("video_id") String video_id, @Param("action_type") String action_type, @Param("comment_text") String comment_text, @Param("comment_id") String comment_id) {
        return commentService.InsertComment(token,video_id,action_type,comment_text,comment_id);
    }

}

