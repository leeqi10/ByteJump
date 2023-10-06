package com.qxy.bytejump.filter;/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.vo.LoginUser;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.RedisCache;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author zzhi
 * @version 1.0
 * @description TODO
 * @createDate 2022/3/1
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //获取token,第一次登录的时候，无token直接放行即可
        String token = request.getParameter("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //从redis获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("登录已过期，请重新刷新登录");
        }
        //将redis用户信息带入到下一个拦截器
        request.setAttribute("loginUserRedis",loginUser);
        //存入SecurityContextHolder
        //TODO 获取权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
