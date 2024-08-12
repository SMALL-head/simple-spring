package com.zyc.springframework.beans.factory.support;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.core.io.Resource;
import com.zyc.springframework.beans.core.io.ResourceLoader;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinition(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
}
