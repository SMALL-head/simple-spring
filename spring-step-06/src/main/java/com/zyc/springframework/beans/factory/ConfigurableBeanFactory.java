package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author zyc
 * @version 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
