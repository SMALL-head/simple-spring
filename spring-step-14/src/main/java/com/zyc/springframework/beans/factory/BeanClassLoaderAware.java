package com.zyc.springframework.beans.factory;

/**
 * @author zyc
 * @version 1.0
 */
public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader);
}
