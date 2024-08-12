package com.zyc.springframework.beans.factory;

/**
 * @author zyc
 * @version 1.0
 */
public interface InitializingBean {
    /**
     * 在Bean对象属性填充完毕后调用
     */
    void afterPropertiesSet() throws Exception;
}
