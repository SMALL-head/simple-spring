package com.zyc.springframework.context;

import com.zyc.springframework.beans.BeansException;

/**
 * @author zyc
 * @version 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;
    void registerShutdownHook();
    void close();
}
