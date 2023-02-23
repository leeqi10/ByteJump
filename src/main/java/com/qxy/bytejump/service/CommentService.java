package com.qxy.bytejump.service;

import com.qxy.bytejump.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.bytejump.entity.response.RePSetComment;
import com.qxy.bytejump.entity.response.RepSelectComment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
public interface CommentService extends IService<Comment> {

    RePSetComment InsertComment(String token,String video_id,String action_type,String comment_text,String comment_id);

    RepSelectComment selectAllComment(String token,String videoId);

}
