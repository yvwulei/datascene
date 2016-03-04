package com.zhongtian.datascene.auth.web.pointcut;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * aspect pointcut for the controller and verifying whether is logined.
 * 
 */
@SuppressWarnings("serial")
@Component("authAdvisor")
public class AuthPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
    /**
     * must be the {@link RequestMapping}
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        RequestMapping mapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (mapping != null) {// 只拦截RequestMapping修饰的方法
            return true;
        }
        return false;
    }
    /**
     * must be {@link Controller}
     */
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return AnnotationUtils.isAnnotationDeclaredLocally(Controller.class, clazz);
               
            }
        };
    }

    @Resource(name = "authAdvice")
    public void setAdvice(Advice advice) {
        super.setAdvice(advice);
    }
}