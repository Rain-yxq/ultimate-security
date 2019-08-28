package com.rain.security.browser;

import com.rain.security.browser.support.SimpleResponse;
import com.rain.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 左边
 * @date 2019/8/26 10:51 PM
 */
@RestController
public class BrowserSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    /**
     * 在跳转之前，会将request请求缓存到HttpSessionRequestCache中
     */
    private RequestCache requestCache = new HttpSessionRequestCache();
    /**
     * spring security提供的用于跳转的工具类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 当需要身份认证时，跳转到这个controller。
     * 在此方法中判断引发跳转的是否是html，如果是
     * @param request  请求
     * @param response 响应
     * @return 如果是html引发的跳转，返回登录页；否则返回401状态码和错误信息
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) // 返回401, "Unauthorized"状态码
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 从缓存中获取请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            // 获取引发认证跳转的url，比如我们直接请求localhost:8080/user.html，那这个就是引发认证跳转的url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的url：" + targetUrl);
            if (targetUrl.endsWith(".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
             }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户至登录页");
    }
}
