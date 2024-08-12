package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
public interface ListableBeanFactory extends BeanFactory {
    <T> Map<String, T> getBeansOfType(Class<?> type) throws BeansException;
    String[] getBeanDefinitionNames();
}
