package com.zyc.springframework.context;

import com.zyc.springframework.core.io.ResourceLoader;
import com.zyc.springframework.beans.factory.ListableBeanFactory;

/**
 * @author zyc
 * @version 1.0
 */
public interface ApplicationContext extends ListableBeanFactory, ResourceLoader, ApplicationEventPublisher {
}