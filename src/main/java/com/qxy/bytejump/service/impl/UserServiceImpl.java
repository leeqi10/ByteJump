package com.qxy.bytejump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qxy.bytejump.config.MonitorExecutionTime;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.UserLikeVideo;
import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.response.UserLR;
import com.qxy.bytejump.entity.vo.LoginUser;
import com.qxy.bytejump.entity.vo.ResponseUser;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.entity.vo.VideoPlus;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
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
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public UserLR register(User user) {

        //判断用户名是否已经存在了
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User hasUser = userMapper.selectOne(queryWrapper);
        if (!Objects.isNull(hasUser)) {
            return new UserLR(403, "注册失败，该用户已存在");
        }

        //加密密码
        PasswordEncoder ps = new BCryptPasswordEncoder();
        String passwordEncoder = ps.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        userMapper.insert(user);
        //添加权限
        List<String> list = new ArrayList<>();
        list.add("list");
        LoginUser loginUser = new LoginUser(user, list);
        //生成token
        String userId = user.getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userId);
        map.put("token", jwt);
        redisCache.setCacheObject("login:" + userId, loginUser, 100, TimeUnit.HOURS);
        return new UserLR(0, "注册成功", Integer.parseInt(userId), jwt);
    }

    @MonitorExecutionTime
    @Override
    public UserLR login(User user) {
        //登录限流策略
        //是否允许通过
        boolean loginIs = redisCache.isAllowedToLogin(user.getUsername(), 5, Duration.ofMinutes(1));
        if (!loginIs) {
            return new UserLR(1, "登录失败,一分钟内只允许登录5次", null, null);
        }
        //使用Authentication authenticate认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //登录失败，给出相应提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败没有足够的权限");
        }
        //如果登录成功 生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userId);
        map.put("token", jwt);
        getTestTime();
        //把完整用户信息保存到redis
        redisCache.setCacheObject("login:" + userId, loginUser, 100, TimeUnit.HOURS);
        return new UserLR(0, "登录成功", Integer.parseInt(userId), jwt);
    }

    @Override
    public ResponseUser getUser(String user_id, String token) {
        User user = userMapper.selectById(user_id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", user_id);
        map.put("name", user.getUsername());
        map.put("follow_count", String.valueOf(user.getFollowCount()));
        map.put("follower_count", String.valueOf(user.getFollowerCount()));
        map.put("is_follow", String.valueOf(user.getIsFollow()));
        ResponseUser<Map> responseUser = new ResponseUser<Map>(0, "查询成功", map);
        return responseUser;
    }

    @Override
    public Result userIsLike(String token, String video_id, String action_Type) {
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //查询有无该视频被该作者的喜欢
        List<UserLikeVideo> userLikeVideos = userMapper.selectAllUserLikeByAll(video_id, userId);
        //赞操作
        if (action_Type.equals("1")) {
            if (userLikeVideos.size() <= 0) {
                userMapper.insertUserLike(video_id, userId, "true");
            } else {
                userMapper.updateUserLike(video_id, userId, "true");

            }
        }
        if (action_Type.equals("2")) {
            userMapper.updateUserLike(video_id, userId, "false");
        }
        //查询自己的视频被喜欢的数目
        List<UserLikeVideo> userLikeVideos1 = userMapper.selectAllUserLikeByVideo(video_id, "true");
        int Likes = userLikeVideos1.size();
        //更新视频被赞的数目
        userMapper.updateFavorite(String.valueOf(Likes), video_id);
        Result result = new Result(0, "操作成功");
        return result;
    }

    @Override
    public RePUserVideo selectAllUserLike(String token, String userId) {
        //查询用户自己的所有喜欢的视频
        List<UserLikeVideo> userLikeVideos = userMapper.selectAllUserLikeByUser(userId, "true");
        //查询所有视频
        List<VideoPlus> videos = videoMapper.selectAllVideo();
        //赋值操作喜欢的视频
        List<VideoPlus> videoLikes = new ArrayList<>();
        //每个自己喜欢的视频
        for (UserLikeVideo userLikeVideo : userLikeVideos
        ) {
            //每个视频
            for (VideoPlus videoPlus : videos
            ) {
                if (userLikeVideo.getVideoId().equals(String.valueOf(videoPlus.getId()))) {
                    videoLikes.add(videoPlus);
                }
            }
        }
        //查询所有用户
        List<User> users = userMapper.selectAllUser();
        //遍历每一个视频
        for (VideoPlus userVideo : videoLikes
        ) {
            //遍历每一个用户
            for (User user : users
            ) {
                if (userVideo.getUserName().equals(user.getUsername())) {
                    userVideo.setAuthor(user);
                }
            }
        }
        RePUserVideo rePUserVideo = new RePUserVideo(0, "查询成功", videoLikes);
        return rePUserVideo;
    }

    @MonitorExecutionTime
    public RePUserVideo getTestTime() {
        for (int i = 0; i < 100000; i++) {

        }
        return null;
    }


}
