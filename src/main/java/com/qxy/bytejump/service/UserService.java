package com.qxy.bytejump.service;

import com.qxy.bytejump.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.bytejump.entity.response.UserLR;
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

    Result user(User user);
}
