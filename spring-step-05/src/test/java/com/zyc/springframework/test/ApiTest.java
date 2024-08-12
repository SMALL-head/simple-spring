package com.zyc.springframework.test;

import com.zyc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zyc.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zyc.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {
    @Test
    public void test_xml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        String s = userService.queryUserInfo();
        System.out.println(s);
    }

}
