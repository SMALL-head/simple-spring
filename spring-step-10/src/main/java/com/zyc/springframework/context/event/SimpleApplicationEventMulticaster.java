package com.zyc.springframework.context.event;

import com.zyc.springframework.beans.factory.BeanFactory;
import com.zyc.springframework.context.ApplicationEvent;
import com.zyc.springframework.context.ApplicationListener;

/**
 * @author zyc
 * @version 1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
