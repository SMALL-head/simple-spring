package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.beans.factory.config.SingletonBeanRegistry;
import com.zyc.springframework.utils.StringValueResolver;

/**
 * @author zyc
 * @version 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver stringValueResolver);

    String resolveEmbeddedValue(String value);

}
