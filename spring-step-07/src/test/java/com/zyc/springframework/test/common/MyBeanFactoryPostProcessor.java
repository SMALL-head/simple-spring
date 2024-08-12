package com.zyc.springframework.test.common;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValue;
import com.zyc.springframework.beans.PropertyValues;
import com.zyc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author  zyc
 * @version  1.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "来自post-processor的修改"));
    }
}
