package com.zyc.springframework.beans.factory;

import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValue;
import com.zyc.springframework.beans.PropertyValues;
import com.zyc.springframework.beans.core.io.DefaultResourceLoader;
import com.zyc.springframework.beans.core.io.Resource;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.beans.factory.config.BeanFactoryPostProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zyc
 * @version 1.0
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(location);
        Properties properties = new Properties();

        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                Object value = propertyValue.getValue();
                if (!(value instanceof String strVal)) continue;
                StringBuilder stringBuilder = new StringBuilder(strVal);
                int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                    String propKey = strVal.substring(startIdx+2, stopIdx);
                    String propVal = properties.getProperty(propKey);
                    stringBuilder.replace(startIdx, stopIdx+1, propVal);

                    // 追加，但是没有删除原来那个占位符格式的PropertyValue
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), stringBuilder.toString()));
                }
            }

        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
