package com.zyc.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvise {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
