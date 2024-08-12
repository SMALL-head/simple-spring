package com.zyc.springframework.beans.factory.support;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author zyc
 * @version 1.0
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
