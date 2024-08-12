package com.zyc.springframework.context.support;

import com.zyc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zyc.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author zyc
 * @version 1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
