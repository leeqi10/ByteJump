package com.qxy.bytejump.service.impl;/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.vo.LoginUser;
import com.qxy.bytejump.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userManager.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }

        //TODO 权限查询
        List<String> list = new ArrayList<>();
        list.add("任何权限");
        //封装为UserDetails
        return new LoginUser(user, list);
    }
}
