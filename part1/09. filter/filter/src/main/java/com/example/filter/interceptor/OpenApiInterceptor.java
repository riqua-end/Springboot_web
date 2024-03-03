package com.example.filter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Handler;
@OpenApi
@Slf4j
@Component // 스프링부트에서 자동으로 컴포넌트스캔을 통해서 빈으로 등록
public class OpenApiInterceptor implements HandlerInterceptor {

    // 요청을 처리 하기 전에 호출
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre handle");
        // true = controller 전달, false = 전달하지 않는다.

        var handlerMethod = (HandlerMethod)handler;

        // 컨트롤러 Method level에서 @OpenApi 가 있는지 확인
        var methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if (methodLevel != null){
            log.info("method level");
            return true; // 컨트롤러에서 메서드 접근 허용
        }
        // 컨트롤러 Class level에서 @OpenApi 가 있는지 확인
        var classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if (classLevel != null){
            log.info("class level");
            return true;
        }

        log.info("open api 아닙니다 : {} ", request.getRequestURI());
        return false; // 컨트롤러에서 메서드 접근 차단
    }

    // 컨트롤러 메서드가 실행 된 후 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handle");
        // HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 뷰가 렌더링 된 후 호출
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("after completion");
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
