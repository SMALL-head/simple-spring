package com.zyc.springframework.aop.framework;

import com.zyc.springframework.aop.AdvisedSupport;
import com.zyc.springframework.aop.AopException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author zyc
 * @version 1.0
 */
public class ByteBuddyAopProxy implements AopProxy {
    private final AdvisedSupport advisedSupport;

    public ByteBuddyAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        ByteBuddy byteBuddy = new ByteBuddy();
        Object object;
        try {
            object = byteBuddy.subclass(advisedSupport.getTargetSource().getTarget().getClass())
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(new ByteBuddyInterceptor(advisedSupport)))
                    .make()
                    .load(Thread.currentThread().getContextClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            throw new AopException("创建ByteBuddy动态代理失败, ", e);
        }
        return object;
    }

}
