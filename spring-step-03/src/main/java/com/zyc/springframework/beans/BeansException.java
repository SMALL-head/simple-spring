package com.zyc.springframework.beans;

/**
 * @author zyc
 * @version 1.0
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
