package com.young.sarangbang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Date : 2022-03-15
 * Author : zilet
 * Project : sarangbang
 * Description :
 */

@Slf4j
@Aspect
@Component
public class AopController {

    @Around("execution(* com.young.sarangbang.controller.home.HomeController.*(..)) ")
    public Object homeProcessing(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
             object = joinPoint.proceed();
        } catch (Throwable e) {
            return errMsg(e.getMessage());
        }

        return object;
    }

    @Around("execution(* com.young.sarangbang.controller.login.LoginController.*(..)) ")
    public Object loginProcessing(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
             object = joinPoint.proceed();
        } catch (Throwable e) {
            return errMsg(e.getMessage());
        }

        return object;
    }


    /**
     *  @AfterThrows 방법를 이용하는 방법도 있음.
     */
    public Object errMsg(String message){
        // HttpServletRequest 접근 방법
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.setAttribute("msg", message);
        return "/controller/error/500";
    }

}
