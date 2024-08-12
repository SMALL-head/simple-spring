package com.zyc.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author zyc
 * @version 1.0
 */
public class ByteBuddyMethodInvocation extends ReflectiveMethodInvocation {
    Callable<?> callable;
    public ByteBuddyMethodInvocation(Object target, Method method, Object[] arguments, Callable<?> call) {
        super(target, method, arguments);
        callable = call;
    }

    @Override
    public Object proceed() throws Throwable {
        return callable.call();
    }
}
