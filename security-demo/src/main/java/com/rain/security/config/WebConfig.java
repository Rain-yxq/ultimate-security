package com.rain.security.config;

import com.rain.security.common.filter.TimeFilter;
import com.rain.security.common.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 左边
 * @date 2019/8/25 2:06 AM
 */
@Configuration // 告诉springboot这是一个配置类
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public FilterRegistrationBean timeFilterRegistration() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        frBean.setFilter(timeFilter);
        // 设置要拦截的路径
        List<String> urls = new ArrayList<>();
        urls.add("/user/page");
        frBean.setUrlPatterns(urls);

        return frBean;
    }
}
