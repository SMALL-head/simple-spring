package com.zyc.springframework.aop;

/**
 * @author zyc
 * @version 1.0
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
