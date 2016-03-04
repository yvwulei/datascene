package com.zhongtian.datascene.sys.web.kit;

import java.util.List;

import javax.servlet.ServletContext;

import com.zhongtian.datascene.auth.dto.LeftMenuDto;
import com.zhongtian.datascene.auth.service.IMenuResService;
import com.zhongtian.datascene.sys.web.context.BeanFactoryContext;

public class RefreshKit {
	public static void refreshLeftMenu(ServletContext ctx) {
		IMenuResService menuResService = (IMenuResService)BeanFactoryContext.getService("menuResService");
		List<LeftMenuDto> mds = menuResService.listLeftNav();
		ctx.setAttribute("leftMenus", mds);
	}
}
