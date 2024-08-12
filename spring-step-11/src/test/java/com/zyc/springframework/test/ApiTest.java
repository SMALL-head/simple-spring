package com.zyc.springframework.test;

import com.zyc.springframework.aop.AdvisedSupport;
import com.zyc.springframework.aop.TargetSource;
import com.zyc.springframework.aop.aspectJ.AspectJExpressionPointCut;
import com.zyc.springframework.aop.framework.ByteBuddyAopProxy;
import com.zyc.springframework.test.bean.IUserService;
import com.zyc.springframework.test.bean.UserService;
import com.zyc.springframework.test.bean.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointCut pointcut = new AspectJExpressionPointCut("execution(* com.zyc.springframework.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");


        System.out.println(pointcut.matches(clazz));            // true
        System.out.println(pointcut.matches(method, clazz));    // true
    }

    @Test
    public void test_dynamic_aop() {
        IUserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut("execution(* com.zyc.springframework.test.bean.UserService.*(..))"));
        advisedSupport.setTargetSource(new TargetSource(userService));

        IUserService proxy = (IUserService) new ByteBuddyAopProxy(advisedSupport).getProxy();
        System.out.println(proxy.queryUserInfo()); // 在原本打印的内容之前会显示Interceptor中的拦截方法
    }

}
