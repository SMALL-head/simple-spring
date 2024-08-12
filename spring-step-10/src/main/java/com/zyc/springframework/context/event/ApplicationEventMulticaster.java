package com.zyc.springframework.context.event;

import com.zyc.springframework.context.ApplicationEvent;
import com.zyc.springframework.context.ApplicationListener;

/**
 * @author zyc
 * @version 1.0
 */
public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
