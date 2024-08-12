package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;

/**
 * @author zyc
 * @version 1.0
 */
public interface FactoryBean<T> {
    T getObject() throws BeansException;

    Class<?> getObjectType();
    boolean isSingleton();
}
