package com.zyc.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.zyc.springframework.beans.BeansException;
import com.zyc.springframework.beans.PropertyValue;
import com.zyc.springframework.core.io.Resource;
import com.zyc.springframework.core.io.ResourceLoader;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.beans.factory.config.BeanReference;
import com.zyc.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.zyc.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.zyc.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException paring XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (var re : resources) {
            loadBeanDefinition(re);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    // 解析XML文件，并且将其中的bean注册到registry中的。
    // 这里再补充一下，factory中存放的构建原料，registry中才是真正存放bean的
    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = reader.read(is);

        org.dom4j.Element root = document.getRootElement();

        // 解析context:component-scan标签
        org.dom4j.Element componentScan = root.element("component-scan");
        if (componentScan != null) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");


        for (Element bean : beanList) {
            // 解析标签
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethodName = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotBlank(id) ? id : name;

            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义bean对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }
            // 填充属性
            List<Element> properties = bean.elements("property");
            for (Element property : properties) {

                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

    }
    private void scanPackage(String scanPath) {
        String[] packages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(packages);
    }
}
