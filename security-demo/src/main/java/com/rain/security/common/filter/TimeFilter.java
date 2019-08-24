package com.rain.security.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器，计算请求通过所有过滤器链的时长
 * @author 左边
 * @date 2019/8/25 1:50 AM
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始计算");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("计算完成");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy...");
    }
}
