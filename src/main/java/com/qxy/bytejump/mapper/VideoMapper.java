package com.qxy.bytejump.mapper;

import com.qxy.bytejump.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxy.bytejump.entity.vo.VideoPlus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface VideoMapper extends BaseMapper<Video> {
    @Insert("insert into video (play_url,user_name,cover_url,title) values (#{path},#{name},#{cover_url},#{title})")
    int insertFile(String path, String name,String cover_url,String title);

    @Select("select id,play_url,cover_url,favorite_count,comment_count,is_favorite,title from video where user_name=#{userName}")
    List<VideoPlus> selectAllVideoByUserName(String userName);
    @Select("select id,play_url,cover_url,favorite_count,comment_count,is_favorite,title,user_name from video limit 30")
    List<VideoPlus> selectAllVideo();

}
