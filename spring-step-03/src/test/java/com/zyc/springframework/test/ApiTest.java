package com.zyc.springframework.test;

import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zyc.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "zz");

        userService.queryUserInfo();

    }

    @Test
    public void test_BeanFactory_noArgs() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");

        userService.queryUserInfo();

    }

}
