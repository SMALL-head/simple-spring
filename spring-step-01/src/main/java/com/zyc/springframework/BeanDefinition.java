package com.zyc.springframework;

/**
 * @author zyc
 * @version 1.0
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
