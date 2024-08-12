package com.zyc;

import com.zyc.springframework.test.bean.ProxyBean;
import net.bytebuddy.ByteBuddy;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zyc
 * @version 1.0
 */
public class ByteBuddyProxyTest {
    @Test
    public void testByteBuddy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ByteBuddy byteBuddy = new ByteBuddy();
        ProxyBean proxyBean = byteBuddy.subclass(ProxyBean.class)
                .make()
                .load(Thread.currentThread().getContextClassLoader())
                .getLoaded()
                .getDeclaredConstructor(String.class)
                .newInstance("abc");
        System.out.println(proxyBean.getClass().getName()); // com.zyc.springframework.test.bean.ProxyBean$ByteBuddy$r7OMOGzF
    }
}
