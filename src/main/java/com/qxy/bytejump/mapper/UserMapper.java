package com.qxy.bytejump.mapper;

import com.qxy.bytejump.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxy.bytejump.entity.vo.UserPlus;
import org.apache.ibatis.annotations.Mapper;
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
public interface UserMapper extends BaseMapper<User> {

    @Select("select id, username,follow_count,follower_count,is_follow from user")
    List<User> selectAllUser();
    @Select("select id, username,follow_count,follower_count,is_follow from user where username=#{userName}")
    User selectAllUserByUserName(String userName);

}
