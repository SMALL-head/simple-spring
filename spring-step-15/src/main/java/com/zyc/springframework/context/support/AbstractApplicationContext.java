package com.zyc.springframework.context.support;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.core.io.DefaultResourceLoader;
import com.zyc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zyc.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.context.ApplicationEvent;
import com.zyc.springframework.context.ApplicationListener;
import com.zyc.springframework.context.ConfigurableApplicationContext;
import com.zyc.springframework.context.event.ApplicationEventMulticaster;
import com.zyc.springframework.context.event.ContextClosedEvent;
import com.zyc.springframework.context.event.ContextRefreshedEvent;
import com.zyc.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.Collection;
import java.util.Map;

/**
 * 继承DefaultResourceLoader是因为
 * @author zyc
 * @version 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;
    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory, 并加载BeanDefinition，
        // 加载过程在AbstractXmlApplicationContext和ClassPathXmlApplication中完成
        refreshBeanFactory();

        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加ApplicationContextAwareProcessor类，让继承自ApplicationContextAware接口的Bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在将Bean对象实例化之前，执行BeanFactoryProcessor操作
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor需要在Bean对象实例化(即第6步)之前注册
        registerBeanPostProcessor(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        // 注：这一步也会有实例化Bean对象的过程，会实例化所有ApplicationListener
        // 我觉得第8步可以提前执行
        registerListeners();

        // 8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
         applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
         beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
