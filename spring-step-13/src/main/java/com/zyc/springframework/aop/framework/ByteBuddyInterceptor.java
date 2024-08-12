package com.zyc.springframework.aop.framework;

import com.zyc.springframework.aop.AdvisedSupport;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author zyc
 * @version 1.0
 */
public class ByteBuddyInterceptor {
    public AdvisedSupport advisedSupport;

    public ByteBuddyInterceptor(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @RuntimeType
    public Object intercept(@Origin Method method, @AllArguments Object[] args, @SuperCall Callable<?> callable) throws Throwable {
        if (advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            // 拦截方法的行为需要自己定义，然后封装进入advisedSupport中
            return advisedSupport.getMethodInterceptor().invoke(new ByteBuddyMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args, callable));
        }
        // callable中已经为我们封装好了参数。
        // 如果我们需要自定义args的话，可以使用method.invoke()
        return callable.call();
    }
}
