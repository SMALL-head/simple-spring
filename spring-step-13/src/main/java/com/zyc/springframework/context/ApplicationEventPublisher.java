package com.zyc.springframework.context;

/**
 * @author zyc
 * @version 1.0
 */
public interface ApplicationEventPublisher {
    /**
     * 通知所有注册过的listeners
     */
    void publishEvent(ApplicationEvent event);
}
