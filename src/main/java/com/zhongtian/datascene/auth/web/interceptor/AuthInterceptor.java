//package com.zhongtian.datascene.auth.web.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.zhongtian.datascene.auth.vo.UserEntity;
//
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//	public final static String userAuth = "userAuth";
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		UserEntity u = (UserEntity) request.getSession().getAttribute(userAuth);
//		if (null == u){
//			StringBuffer sb = new StringBuffer(request.getContextPath());
//    		sb.append("/user/login");	
//    		response.sendRedirect(sb.toString());
//    		return false;
//		}else
//			return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	}
//}