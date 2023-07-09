package com.qxy.bytejump.mapper;

import com.qxy.bytejump.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxy.bytejump.entity.UserLikeVideo;
import com.qxy.bytejump.entity.vo.UserPlus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface UserMapper extends BaseMapper<User> {

    @Select("select id, username,follow_count,follower_count,is_follow from user")
    List<User> selectAllUser();
    @Select("select id, username,follow_count,follower_count,is_follow from user where username=#{userName}")
    User selectAllUserByUserName(String userName);
    @Select("select id,video_id,user_id,is_favorite from userlikevideo where user_id=#{userId} and is_favorite=#{isFavorite}")
    List<UserLikeVideo> selectAllUserLikeByUser(String userId,String isFavorite);

    @Select("select id,video_id,user_id,is_favorite from userlikevideo where video_id=#{videoId} and user_id=#{userId}")
    List<UserLikeVideo> selectAllUserLikeByAll(String videoId,String userId);

    @Select("select id,video_id,user_id,is_favorite from userlikevideo where video_id=#{videoId} and is_favorite=#{isFavorite}")
    List<UserLikeVideo> selectAllUserLikeByVideo(String videoId,String isFavorite);

    @Insert("insert into userlikevideo(video_id,user_id,is_favorite) values(#{videoId},#{userId},#{isFavorite})")
    int insertUserLike(String videoId,String userId,String isFavorite);
    @Update("update userlikevideo set is_favorite=#{isFavorite} where video_id=#{videoId} and user_id=#{userId} ")
    int updateUserLike(String videoId,String userId,String isFavorite);


    @Update("update video set favorite_count=#{favorites} where id=#{videoId}")
    int updateFavorite(String favorites,String videoId);

}
