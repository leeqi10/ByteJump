package com.qxy.bytejump.handler;/**
 * @author lingqu
 * @date 2022/3/6
 * @apiNote
 */

import com.alibaba.fastjson.JSON;
import com.qxy.bytejump.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *@description TODO
 *@author leeqi10
 *@createDate 2023/1/25
 *@version 1.0
 */

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        StringBuffer requestURL = request.getRequestURL();
        log.info("requestURL {}",requestURL);
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.FORBIDDEN.value());
        map.put("msg", HttpStatus.FORBIDDEN.getReasonPhrase());
        String json = JSON.toJSONString(map);
        log.info("json::{}",json);
        WebUtils.renderString(response,json);
    }
}
