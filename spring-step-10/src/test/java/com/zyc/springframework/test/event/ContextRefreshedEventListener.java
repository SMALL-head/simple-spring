package com.zyc.springframework.test.event;

import com.zyc.springframework.context.ApplicationListener;
import com.zyc.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zyc
 * @version 1.0
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + event.getClass().getName());
    }

}
