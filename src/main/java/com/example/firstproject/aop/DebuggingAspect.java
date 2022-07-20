package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언: 부가 기능을 주입하는 클래스
@Component
@Slf4j
public class DebuggingAspect {

    //어느 대상을 타겟으로 할건가 모든 api패키지에 있는 메소드 -> 서비스도 다 적용됨
    //@Pointcut("execution(* com.example.firstproject.create(..))")
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut(){ }

    //실행 시점 설정 -> cut(CommentService.create())이전에 실행됨
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){ //cut()의 대상 메소드
        //입력값 가져오기
        Object[] args = joinPoint.getArgs();
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        //입력값 로깅하기기

        //CommentService#create()의 입력값 -> 5
        //CommentService#create()의 입력값 -> CommentDto(id=null, ...)

        for(Object obj : args){
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }
    //실행 시점 설정: cut()에 지정된 대상 호출 성공 후!
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, //cut()의 대상 메소드
                                   Object returnObj){ // 리턴값값
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        //반환값 로깅
        //CommentService#create()의 반환값 -> CommentDto(id=10, ...)
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
