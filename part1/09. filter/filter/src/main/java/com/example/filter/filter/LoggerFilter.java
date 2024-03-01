package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
// LoggerFilter 클래스가 표준 Servlet Filter 인터페이스를 구현하도록 하여 HTTP 요청 및 응답을 가로채서 처리할 수 있도록 합니다.
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 일반적으로 Servlet은 Http 요청 및 응답 본문을 한번만 읽을 수 있다.

        // 진입전
        log.info(">>>>>> 진입");

        /*var req = new HttpServletRequestWrapper((HttpServletRequest) request);

        var res = new HttpServletResponseWrapper((HttpServletResponse) response);

        var br = req.getReader();

        var list = br.lines().toList();

        list.forEach(it -> {
            log.info("{}",it);
        });

        java.lang.IllegalStateException: getReader() has already been called for this request

        req.getReader() 로 이미 데이터를 읽어버렸기 때문에 컨트롤러에서 매핑이 안되고
        위와 같이 IllegalStateException 에러가 뜬다.

        */

        // ContentCaching 을 사용하면 본문을 캐싱하여 나중에 다시 읽을 수 있다.

        /*캐싱을 사용하는 이유

        후속 처리: 요청 또는 응답 본문을 여러 번 읽고 처리해야 하는 경우 캐싱을 통해 성능을 향상시킬 수 있습니다.
        로그 기록: 요청 및 응답 본문을 로깅해야 하는 경우 캐싱을 통해 본문 내용을 쉽게 저장하고 분석할 수 있습니다.
        보안: 요청 또는 응답 본문에 민감한 정보가 포함된 경우 캐싱을 통해 정보를 보호할 수 있습니다.

        */

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req,res);

        /*
            getContentAsByteArray ?
            ContentCachingRequestWrapper 또는 ContentCachingResponseWrapper 에서 제공되는 메서드입니다.
            이 메서드는 캐싱된 요청 또는 응답 본문을 바이트 배열로 반환합니다.
            캐싱된 요청 및 응답 본문을 문자열로 변환하여 로깅하기 위해 사용함
         */

        var reqJson = new String(req.getContentAsByteArray());
        log.info("req : {}",reqJson);
        var resJson = new String(res.getContentAsByteArray());
        log.info("res : {}",resJson);

        log.info("<<<<<< 리턴");
        // 진입후

        res.copyBodyToResponse(); // ContentCachingResponseWrapper에서 응답 본문을 다시 실제 응답 객체로 복사합니다.

    }
}
