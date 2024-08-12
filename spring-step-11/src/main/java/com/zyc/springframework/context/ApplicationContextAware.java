package com.zyc.springframework.context;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.factory.Aware;

/**
 * @author zyc
 * @version 1.0
 */
public interface ApplicationContextAware extends Aware {
     void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
