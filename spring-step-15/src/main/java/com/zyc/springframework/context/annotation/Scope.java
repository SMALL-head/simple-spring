package com.zyc.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author zyc
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
