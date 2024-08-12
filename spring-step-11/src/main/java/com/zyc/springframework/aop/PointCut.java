package com.zyc.springframework.aop;

/**
 * @author zyc
 * @version 1.0
 */
public interface PointCut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
