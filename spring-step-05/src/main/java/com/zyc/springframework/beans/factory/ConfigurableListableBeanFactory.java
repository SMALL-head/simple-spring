package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zyc
 * @version 1.0
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
