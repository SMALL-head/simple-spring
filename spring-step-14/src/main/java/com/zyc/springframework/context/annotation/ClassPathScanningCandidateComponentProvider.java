package com.zyc.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.zyc.springframework.beans.factory.config.BeanDefinition;
import com.zyc.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 给定basePackage，查找所有带有Component注解的类
 * @author zyc
 * @version 1.0
 */
public class ClassPathScanningCandidateComponentProvider {
    /**
     *
     * @param basePackage 需要扫描的包路径
     * @return 带有注解 @ Component所封装的BeanDefinition类
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);

        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }

        return candidates;
    }
}
