package com.zyc.springframework.test;

import com.zyc.springframework.context.support.ClassPathXmlApplicationContext;
import com.zyc.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * @author zyc
 * @version 1.0
 */

public class ApiTest {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1234L, "成功！"));
        applicationContext.registerShutdownHook();

    }

}
