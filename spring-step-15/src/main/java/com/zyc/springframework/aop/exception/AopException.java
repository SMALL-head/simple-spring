package com.zyc.springframework.aop.exception;

/**
 * @author zyc
 * @version 1.0
 */
public class AopException extends RuntimeException{
     public AopException(String msg) {
         super(msg);
     }

    public AopException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
