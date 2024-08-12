package com.zyc.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zyc
 * @version 1.0
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
