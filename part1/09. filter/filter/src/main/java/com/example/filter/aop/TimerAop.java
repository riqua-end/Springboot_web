package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect // AOP Aspect 클래스로 인식
@Component //스프링 컨테이너에 자동 등록될 수 있도록 컴포넌트로 설정
public class TimerAop {

    // UserApiController 내의 모든 메서드에 pointcut을 적용
    @Pointcut(value = "within(com.example.filter.controller.UserApiController)")
    public void timerPointCut(){

    }

    /*
    *   @Before: Pointcut에 매칭되는 메서드 실행 전에 실행됩니다.
        @After: Pointcut에 매칭되는 메서드 실행 후에 실행됩니다 (성공 또는 실패 여부에 관계없이).
        @AfterReturning: Pointcut에 매칭되는 메서드가 성공적으로 실행된 후에 실행됩니다.
        @AfterThrowing: Pointcut에 매칭되는 메서드가 예외를 발생시킨 후에 실행됩니다.
        @Around: Pointcut에 매칭되는 메서드 실행 전후에 실행됩니다. 매개변수, 반환 값을 변경하거나 메서드 실행을 완전히 막을 수도 있습니다.
    */

    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint){ // JoinPoint: 현재 실행 지점에 대한 정보를 제공하는 객체
        System.out.println("before");
    }

    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("after returning");
    }

    @AfterThrowing(value = "timerPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("after throwing");
    }


    @Around(value = "timerPointCut()")
    // ProceedingJoinPoint: joinPoint.proceed() 메서드를 호출하여 실제 메서드를 실행 할 수 있음
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("메서드 실행 이전");

        // UserRequest 객체의 phoneNumber 형식 변환
        Arrays.stream(joinPoint.getArgs()).forEach(
                it -> {
                    if (it instanceof UserRequest tempUser){
                        var phoneNumber = tempUser.getPhoneNumber().replace("-","");
                        tempUser.setPhoneNumber(phoneNumber);
                    }
                }
        );
        // 암호화/복호화 / 로깅 등을 위한 새로운 객체 생성 , 비즈니스 로직은 따로 구현해야됨
        var newObjs = Arrays.asList(
                new UserRequest()
        );

        // 성능 검사 start(),stop()
        var stopWatch = new StopWatch();

        stopWatch.start();

        // 메서드 실행 진행
        joinPoint.proceed(newObjs.toArray());

        stopWatch.stop();

        //실행 시간 측정 및 출력
        System.out.println("총 소요 시간 MS = "+ stopWatch.getTotalTimeMillis());

        System.out.println("메서드 실행 이후");
    }

}
