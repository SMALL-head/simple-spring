package com.zyc.springframework.test;

import com.zyc.springframework.context.support.ClassPathXmlApplicationContext;
import com.zyc.springframework.test.bean.IUserService;
import org.junit.Test;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {

    @Test
    public void test_aop_with_field_injection() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService bean = applicationContext.getBean("userService", IUserService.class);
        System.out.println(bean.queryUserInfo());
    }
}
