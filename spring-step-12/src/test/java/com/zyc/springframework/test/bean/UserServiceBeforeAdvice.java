package com.zyc.springframework.test.bean;

import com.zyc.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[UserServiceBeforeAdvice]-[before]-拦截方法: " + method.getName());
    }
}
