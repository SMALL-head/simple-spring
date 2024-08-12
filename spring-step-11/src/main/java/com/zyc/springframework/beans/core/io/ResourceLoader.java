package com.zyc.springframework.beans.core.io;

/**
 * @author zyc
 * @version 1.0
 */
public interface ResourceLoader {
    String CLASS_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
