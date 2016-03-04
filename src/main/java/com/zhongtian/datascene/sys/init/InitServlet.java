package com.zhongtian.datascene.sys.init;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhongtian.datascene.basic.util.PropertiesUtil;
import com.zhongtian.datascene.sys.web.context.BeanFactoryContext;
import com.zhongtian.datascene.sys.web.kit.RefreshKit;


public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	private static String realpath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		realpath = config.getServletContext().getRealPath("");
		/*List<String> orgTypes = initOrg();
		List<Integer> otids = initOrgTypeId(wc, orgTypes);
		System.out.println(otids);
		config.getServletContext().setAttribute("existOrgTypes", otids);*/
		BeanFactoryContext.setWc(wc);
		
		initLeftNavMenu(config.getServletContext());
	}
	
	
	private void initLeftNavMenu(ServletContext servletContext) {
		RefreshKit.refreshLeftMenu(servletContext);
	}


/*	private List<Integer> initOrgTypeId(WebApplicationContext wc,List<String> orgTypes) {
		IOrgTypeService orgTypeService = (IOrgTypeService)wc.getBean("orgTypeService");
		List<Integer> otids = new ArrayList<Integer>();
		for(String ot:orgTypes) {
			otids.add(orgTypeService.loadBySn(ot).getId());
		}
		return otids;
	}*/
	
	@SuppressWarnings("rawtypes")
	private List<String> initOrg() {
		Properties prop = PropertiesUtil.getInstance().load("zzjg");
		Enumeration e =  prop.propertyNames();
		List<String> orgs = new ArrayList<String>();
		while(e.hasMoreElements()) {
			orgs.add(prop.getProperty(e.nextElement().toString()));
		}
		return orgs;
	}

	public static String getRealpath() {
		return realpath;
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}

}
