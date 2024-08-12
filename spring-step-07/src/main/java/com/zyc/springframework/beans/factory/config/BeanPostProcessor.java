package com.zyc.springframework.beans.factory.config;

import com.zyc.springframework.beans.BeansException;

/**
 * 这个接口提供了对修改新实例化Bean对象的扩展点。
 * 和BeanFactoryPostProcess相比，后者是对BeanDefinition的后置修改，而这个是直接对实例化的bean做修改
 * @author zyc
 * @version 1.0
 */
public interface BeanPostProcessor {
    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean 初始化的bean
     * @param beanName bean对应的名称
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean 初始化的bean
     * @param beanName bean对应的名称
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
