package com.zyc.springframework.test;

import com.zyc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zyc.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zyc.springframework.context.support.ClassPathXmlApplicationContext;
import com.zyc.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

}
