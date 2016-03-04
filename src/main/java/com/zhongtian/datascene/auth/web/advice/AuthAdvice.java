package com.zhongtian.datascene.auth.web.advice;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhongtian.datascene.auth.annotation.AuthRequired;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.basic.excepiton.AuthException;

/**
 * verify whether user is authed
 * 
 */
@Component("authAdvice")
public class AuthAdvice implements MethodBeforeAdvice {
	@SuppressWarnings("rawtypes")
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();// 获取request

		Class clazz = target.getClass();
		AuthRequired needAuthClazz = AnnotationUtils.findAnnotation(clazz, AuthRequired.class);
		AuthRequired needAuthMethod = AnnotationUtils.findAnnotation(method, AuthRequired.class);
		AuthRequired needAuth = needAuthMethod==null?needAuthClazz:needAuthMethod;
		if (needAuth != null && needAuth.value()) {
			UserEntity u = (UserEntity) request.getSession().getAttribute("userAuth");
			if (null == u) {
				StringBuffer sb = new StringBuffer(request.getContextPath());
				sb.append("/user/login");
				throw new AuthException("not login");
			}
		}

	}
}
