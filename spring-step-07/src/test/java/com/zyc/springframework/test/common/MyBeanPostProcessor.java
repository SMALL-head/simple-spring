package com.zyc.springframework.test.common;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.test.bean.UserService;

/**
 * @author zyc
 * @version 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
