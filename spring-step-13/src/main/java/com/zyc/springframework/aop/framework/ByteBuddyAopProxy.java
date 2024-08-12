package com.zyc.springframework.aop.framework;

import com.zyc.springframework.aop.AdvisedSupport;
import com.zyc.springframework.aop.exception.AopException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

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
