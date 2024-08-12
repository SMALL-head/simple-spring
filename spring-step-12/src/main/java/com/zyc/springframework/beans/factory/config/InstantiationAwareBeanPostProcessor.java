package com.zyc.springframework.beans.factory.config;

import com.zyc.springframework.beans.BeansException;

/**
 * @author zyc
 * @version 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
