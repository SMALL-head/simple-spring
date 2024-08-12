package com.zyc.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValues;
import com.zyc.springframework.beans.factory.AutowireCapableBeanFactory;
import com.zyc.springframework.beans.factory.DisposableBean;
import com.zyc.springframework.beans.factory.InitializingBean;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.beans.factory.config.BeanPostProcessor;
import com.zyc.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiate bean failed", e);
        }

        // 注册实现了DisposableBean接口的对象
        registerDisposableBeanIfNecessary(beanName, bean,beanDefinition);

        registerSingleton(beanName, bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (var propertyValue : propertyValues.getPropertyValueList()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            if (value instanceof BeanReference v) {
                value = getBean(v.getBeanName());
            }

            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行BeanPostProcessor Before前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2. 执行bean对象的init方法
        try {
            invokeInitMethod(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 3. 执行BeanPostProcessor After 后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    private void invokeInitMethod(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
        if (wrappedBean instanceof InitializingBean initializingBean) {
            initializingBean.afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) && !(wrappedBean instanceof InitializingBean)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            initMethod.invoke(wrappedBean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return null;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return null;
            }
            result = current;
        }
        return result;
    }
}