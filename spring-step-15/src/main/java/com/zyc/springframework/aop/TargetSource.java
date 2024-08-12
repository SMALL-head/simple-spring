package com.zyc.springframework.aop;

import com.zyc.springframework.utils.ClassUtils;

/**
 * @author zyc
 * @version 1.0
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isByteBuddyProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
