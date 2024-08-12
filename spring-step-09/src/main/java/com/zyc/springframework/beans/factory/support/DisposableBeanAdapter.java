package com.zyc.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.zyc.springframework.beans.factory.DisposableBean;
import com.zyc.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @version 1.0
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean disposableBean) {
            disposableBean.destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            method.invoke(bean);
        }
    }
}
