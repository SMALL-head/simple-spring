package com.zyc.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValues;
import com.zyc.springframework.beans.factory.BeanFactory;
import com.zyc.springframework.beans.factory.BeanFactoryAware;
import com.zyc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zyc.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zyc.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

/**
 * @author zyc
 * @version 1.0
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isByteBuddyProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();

        // 处理注解 @Value
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation == null) {
                continue;
            }
            String value = valueAnnotation.value();
            value = beanFactory.resolveEmbeddedValue(value);
            BeanUtil.setFieldValue(bean, field.getName(), value);

        }
        // 处理注解 @Autowired
        for (Field field : declaredFields) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation == null) {
                continue;
            }
            Class<?> type = field.getType();
            String dependentBeanName = null;
            Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
            Object dependentBean = null;

            if (null != qualifierAnnotation) {
                dependentBeanName = qualifierAnnotation.value();
                dependentBean = beanFactory.getBean(dependentBeanName, type);
            } else {
                dependentBean = beanFactory.getBean(type);
            }

            BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
        }
        return propertyValues;
    }
}
