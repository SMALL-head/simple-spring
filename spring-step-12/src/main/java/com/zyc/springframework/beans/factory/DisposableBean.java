package com.zyc.springframework.beans.factory;

/**
 * @author zyc
 * @version 1.0
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
