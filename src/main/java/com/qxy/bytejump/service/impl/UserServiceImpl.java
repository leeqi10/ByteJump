package com.qxy.bytejump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.response.UserLR;
import com.qxy.bytejump.entity.vo.LoginUser;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public UserLR register(User user) {

        //判断用户名是否已经存在了
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User hasUser = userMapper.selectOne(queryWrapper);
        if (!Objects.isNull(hasUser)){
            return new UserLR(403,"注册失败，该用户已存在");
        }

        //加密密码
        PasswordEncoder ps = new BCryptPasswordEncoder();
        String passwordEncoder = ps.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        userMapper.insert(user);

        //生成token
        String userId = user.getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<String,String>();
        map.put("user_id",userId);
        map.put("token", jwt);
        return new UserLR(200,"注册成功",Integer.parseInt(userId),jwt);
    }

    @Override
    public UserLR login(User user) {
        //使用Authentication authenticate认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //登录失败，给出相应提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果登录成功 生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<String,String>();
        map.put("user_id",userId);
        map.put("token", jwt);
        //把完整用户信息保存到redis
        redisCache.setCacheObject("login:"+userId, loginUser,1, TimeUnit.HOURS);
        return new UserLR(200,"登录成功",Integer.parseInt(userId),jwt);
    }

    @Override
    public Result user(User user) {
        return new Result(200,"登录成功");
    }
}
