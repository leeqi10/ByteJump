package com.qxy.bytejump.service;

import com.qxy.bytejump.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.response.UserLR;
import com.qxy.bytejump.entity.vo.ResponseUser;
import com.qxy.bytejump.entity.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
public interface UserService extends IService<User> {
    /**
     *
     * @param user
     * @return
     * @apiNote 注册
     */
    UserLR register(User user);

    /**
     *
     * @param user
     * @return
     * @apiNote 登录
     */
    UserLR login(User user);
    /**
     *
     * @param user_id ,用户id
     * @param token ,用户token
     * @return
     * @apiNote 获取用户信息
     */

    ResponseUser getUser(String user_id, String token);
    /**
     *
     * @param token
     * @param video_id
     * @param action_Type ,1是点赞，2是取消点赞
     * @return
     * @apiNote 获取用户信息
     */
    Result userIsLike(String token,String video_id,String action_Type);

    RePUserVideo selectAllUserLike(String token,String userId);
}
