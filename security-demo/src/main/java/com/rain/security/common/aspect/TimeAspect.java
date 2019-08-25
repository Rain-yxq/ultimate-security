package com.rain.security.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 左边
 * @date 2019/8/25 3:26 PM
 */
@Component
@Aspect
public class TimeAspect {

    @Around("execution(* com.rain.security.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect start...");

        // 获取controller的入参
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("param is :" + arg);
        }

        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        System.out.println("aspect 耗时：" + (System.currentTimeMillis() - start));
        System.out.println("aspect end...");

        return result;
    }
}
