package com.zyc.springframework.beans.factory.config;

/**
 * 注册和获取单例对象的接口
 * @author zyc
 * @version 1.0
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void registerSingleton(String name, Object singletonObject);
}
