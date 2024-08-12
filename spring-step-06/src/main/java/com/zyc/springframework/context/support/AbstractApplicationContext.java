package com.zyc.springframework.context.support;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.core.io.DefaultResourceLoader;
import com.zyc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zyc.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory, 并加载BeanDefinition
        refreshBeanFactory();
        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在将Bean对象实例化之前，执行BeanFactoryProcessor操作
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor需要在Bean对象实例化之前注册
        registerBeanPostProcessor(beanFactory);

        // 5. 提前实例化单例Bean对象(在本案例中，所有bean都是单例的)
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建BeanFactory，并向其中注册BeanDefinition
     */
    protected abstract void refreshBeanFactory() throws BeansException;
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (var beanFactoryPostProcessor : beansOfType.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);

        for (BeanPostProcessor beanPostProcessor : beansOfType.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
