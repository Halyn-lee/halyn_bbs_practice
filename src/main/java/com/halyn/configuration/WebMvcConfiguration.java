package com.halyn.configuration;

import com.halyn.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

    private final String[] excludePaths = {"/css/**", "/js/**", "/login", "/error", "/join", "/insertMember.do"}; // 컨트롤러 경로
    private final String[] addPaths = {"/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns(excludePaths).addPathPatterns(addPaths);
    }
}