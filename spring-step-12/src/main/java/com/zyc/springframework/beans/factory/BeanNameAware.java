package com.zyc.springframework.beans.factory;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
