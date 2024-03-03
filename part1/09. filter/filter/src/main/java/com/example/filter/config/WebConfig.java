package com.example.filter.config;

import com.example.filter.interceptor.OpenApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 설정 클래스로 지정 , 스프링 컨테이너에 bean으로 등록됨
public class WebConfig implements WebMvcConfigurer {

    // interceptor 등록
    @Autowired
    private OpenApiInterceptor openApiInterceptor;

    // 스프링 애플리케이션이 사용할 OpenApiInterceptor를 명시적으로 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(openApiInterceptor)
                .addPathPatterns("/**"); // root 하위에 있는 모든 주소를 매핑
    }
}
