package com.qxy.bytejump.service.impl;/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.vo.LoginUser;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zzhi
 * @version 1.0
 * @description TODO
 * @createDate 2022/3/1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //先从request-redis缓存里面查询是否有该用户
        //TODO 权限查询
        List<String> list = new ArrayList<>();
        list.add("任何权限");

        LoginUser loginUser = (LoginUser) request.getAttribute("loginUserRedis");
        if (!StringUtils.isEmpty(loginUser)){
            loginUser.setPermissions(list);
            return loginUser;
        }

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userManager.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }

        //封装为UserDetails
        return new LoginUser(user, list);
    }
}
