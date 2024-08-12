package com.zyc.springframework.test;

import com.zyc.springframework.aop.AdvisedSupport;
import com.zyc.springframework.aop.TargetSource;
import com.zyc.springframework.aop.aspectJ.AspectJExpressionPointcut;
import com.zyc.springframework.aop.framework.ByteBuddyAopProxy;
import com.zyc.springframework.beans.core.io.ClassPathResource;
import com.zyc.springframework.context.support.ClassPathXmlApplicationContext;
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
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }


}
