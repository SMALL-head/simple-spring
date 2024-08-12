package com.zyc.springframework.beans.factory.config;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的BeanDefinition加载完成后，且将Bean对象实例化之前，提供修改BeanDefinition属性的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
