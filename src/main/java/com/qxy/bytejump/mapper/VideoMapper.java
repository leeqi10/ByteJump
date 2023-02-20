package com.qxy.bytejump.mapper;

import com.qxy.bytejump.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    @Insert("insert into video (play_url,user_name) values (#{path},#{name})")
    int insertFile(String path, String name);
}
