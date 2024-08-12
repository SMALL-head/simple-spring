package com.zyc.springframework.beans.factory.support;

import com.zyc.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
