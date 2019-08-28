package com.rain.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 左边
 * @date 2019/8/28 1:06 AM
 */
@Component("myAuthenticationHandler")
public class MyAuthenticationHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 自定义登陆成功后的处理行为
     * @param request 页面的请求
     * @param response 给页面的响应
     * @param authentication 用户的认证信息，包括我们之前登陆后的UserDetails等信息，用于写回给前端。
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        logger.info("登陆成功");
        response.setContentType("application/json;charset=UTF-8");
        // writeValueAsString类似于fastjson的toJSONString
        response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
