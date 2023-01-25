package com.qxy.bytejump.service.impl;

import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
