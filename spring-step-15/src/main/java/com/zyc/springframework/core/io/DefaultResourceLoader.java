package com.zyc.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * @author zyc
 * @version 1.0
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASS_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASS_URL_PREFIX.length()));
        } else {
            try {
                URL url = URI.create(location).toURL();
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
