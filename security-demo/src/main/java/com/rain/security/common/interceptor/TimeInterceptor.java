package com.rain.security.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器会拦截所有controller的方法调用。
 * preHandle: controller调用前执行。
 * postHandle: controller调用完成后的执行，但如果发生异常该方法不执行。
 * afterCompletion: 请求处理完成后的回调，抛出异常也会执行。
 *
 * @author 左边
 * @date 2019/8/25 2:28 AM
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());

        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+ (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+ (System.currentTimeMillis() - start));
        System.out.println("ex is "+ex);
    }
}
