package com.zyc.springframework.beans.stereotype;

import java.lang.annotation.*;

/**
 * @author zyc
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
