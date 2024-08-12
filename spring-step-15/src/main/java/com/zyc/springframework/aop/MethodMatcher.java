package com.zyc.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
