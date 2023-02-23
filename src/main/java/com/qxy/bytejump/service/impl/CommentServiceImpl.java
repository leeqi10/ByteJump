package com.qxy.bytejump.service.impl;

import com.qxy.bytejump.entity.Comment;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.response.RePSetComment;
import com.qxy.bytejump.entity.response.RepSelectComment;
import com.qxy.bytejump.entity.vo.SetComment;
import com.qxy.bytejump.entity.vo.UserPlus;
import com.qxy.bytejump.mapper.CommentMapper;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.bytejump.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public RePSetComment InsertComment(String token, String video_id, String action_type, String comment_text, String comment_id) {
        //获取评论用户的id
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //获得系统日期
        Date date = new Date();
        String time = date.toString();
        //查询用户
        User user = userMapper.selectById(userId);
        //获得用户名字
        String userName = user.getUsername();
        //获取到插入评论之后的id
        String commentId="";
        //发布发布评论
        if (action_type.equals("1")){
            commentMapper.insertComment(userName,comment_text,time,video_id);
            commentId=commentMapper.selectId(time);
        }
        if (action_type.equals("2")){
            commentMapper.DeleteComment(comment_id);
        }

        //将user转换成userPlus
        UserPlus userPlus = new UserPlus();
        userPlus.setId(user.getId());
        userPlus.setName(userName);
        userPlus.setFollow_count(user.getFollowCount());
        userPlus.setFollower_count(user.getFollowerCount());
        userPlus.setIs_follow(user.getIsFollow());
        SetComment setComment = new SetComment(Integer.parseInt(commentId),userPlus,comment_text,time);
        RePSetComment rePSetComment = new RePSetComment(0,"评论成功",setComment);
        return rePSetComment;
    }

    @Override
    public RepSelectComment selectAllComment(String token, String videoId) {
        //查询这条视频的所有评论
        List<Comment> commentList = commentMapper.selectAllComment(videoId);
        //注入到返回评论集合的实体集且注入作者信息
        List<SetComment> setComments=new ArrayList<>();
        for (Comment comment:commentList
             ) {
            //个体注入
            SetComment setComment = new SetComment();
            setComment.setId(comment.getId());
            setComment.setContent(comment.getContent());
            setComment.setCreateData(comment.getCreateData());
            //查询用户评论的信息
            User user = userMapper.selectAllUserByUserName(comment.getUserName());
            UserPlus userPlus = new UserPlus();
            userPlus.setId(user.getId());
            userPlus.setName(user.getUsername());
            userPlus.setFollow_count(user.getFollowCount());
            userPlus.setFollower_count(user.getFollowerCount());
            userPlus.setIs_follow(user.getIsFollow());
            setComment.setUser(userPlus);
            setComments.add(setComment);
        }
        RepSelectComment repSelectComment = new RepSelectComment(0,"查询成功",setComments);
        return repSelectComment;
    }
}
