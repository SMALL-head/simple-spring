package com.zyc.springframework.test.event;

import com.zyc.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author zyc
 * @version 1.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.printf("收到:%s消息；时间:%s%n", event.getSource(), new Date());
        System.out.printf("消息: %s；内容:%s%n", event.getId(), event.getMessage());
    }
}
