package com.zyc.springframework.context;

import java.util.EventListener;

/**
 * @author zyc
 * @version 1.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
