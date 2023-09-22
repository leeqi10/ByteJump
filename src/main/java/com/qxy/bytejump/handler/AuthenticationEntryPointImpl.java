package com.qxy.bytejump.handler;/**
 * @author lingqu
 * @date 2022/3/6
 * @apiNote
 */

import com.alibaba.fastjson.JSON;
import com.qxy.bytejump.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leqi10
 * @version 1.0
 * @description TODO
 * @createDate 2023/1/25
 */

@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        StringBuffer requestURL = request.getRequestURL();
        log.info("requestURL {}", requestURL);
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        String json = JSON.toJSONString(map);
        log.info("json::{}", json);
        WebUtils.renderString(response, json);
    }
}
