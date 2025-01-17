package com.zyc.springframework.aop.aspectJ;

import com.zyc.springframework.aop.Pointcut;
import com.zyc.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author zyc
 * @version 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 具体的拦截方法
     */
    private Advice advice;
    /**
     * 表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
