package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
