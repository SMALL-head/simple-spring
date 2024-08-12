package com.zyc.springframework.beans.factory.config;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValues;

/**
 * @author zyc
 * @version 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException;

}
