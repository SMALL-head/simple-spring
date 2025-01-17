package com.zyc.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author zyc
 * @version 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    /**
     * The actual value expression. e.g. "${systemProperties.myProp}"
     */
    String value();
}
