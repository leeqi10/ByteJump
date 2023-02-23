package com.qxy.bytejump.mapper;

import com.qxy.bytejump.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("insert into comment(user_name,content,create_data,video_id) values (#{userName},#{commentText},#{CreateTime},#{videoId})")
    int insertComment(String userName,String commentText,String CreateTime,String videoId);
    @Delete("delete from comment where id=#{id}")
    int DeleteComment(String id);
    @Select("select id from comment where create_data=#{time}")
    String selectId(String time);

    @Select("select * from comment where video_id=#{videoId}")
    List<Comment> selectAllComment(String videoId);

    @Update("update video set comment_count=#{comments} where id=#{videoId}")
    int updateComments(String comments,String videoId);
}
