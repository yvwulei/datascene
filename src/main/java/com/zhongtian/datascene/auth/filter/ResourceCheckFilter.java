package com.zhongtian.datascene.auth.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zhongtian.datascene.basic.util.JsonUtil;
import com.zhongtian.datascene.sys.viewvo.Response;

public class ResourceCheckFilter extends AccessControlFilter {
	protected static final Log logger = LogFactory.getLog(ResourceCheckFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		logger.debug("excute method:isAccessAllowed ...");
		Subject subject = getSubject(request, response);
		String url = getPathWithinApplication(request);
		logger.debug("url:"+url);
		return subject.isPermitted(url);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		logger.debug("excute method:onAccessDenied ...");
		HttpServletResponse hsp = (HttpServletResponse)response;
		HttpServletRequest hReq = (HttpServletRequest)request;
		hsp.setContentType("text/html");
		hsp.setCharacterEncoding("UTF-8");      //解决中文乱码问题
		PrintWriter out = response.getWriter();
		String json=JsonUtil.getInstance().obj2json(new Response().failure("unauth"));//;json处理工具类 
		out.write(json.toString());
		out.flush();
		out.close();
		return false;
	}

}
