package com.zyc.springframework.beans.factory.support;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import net.bytebuddy.ByteBuddy;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * cglib 不支持jdk17+版本，因此我们采用ByteBuddy来代替cglib
 *
 * @author zyc
 * @version 1.0
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        ByteBuddy byteBuddy = new ByteBuddy();

        try {
            return byteBuddy.subclass(beanDefinition.getBeanClass())
                    .make()
                    .load(CglibSubclassingInstantiationStrategy.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(ctor != null ? ctor.getParameterTypes() : null)
                    .newInstance(args);
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new BeansException("instantiate "+ beanDefinition.getBeanClass().getName() + " failed: ", e);
        }
    }
}
