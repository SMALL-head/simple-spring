package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.Aware;
import com.zyc.springframework.beans.factory.BeanFactory;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
