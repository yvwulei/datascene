package com.zhongtian.datascene.auth.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhongtian.datascene.auth.annotation.AuthOper;
import com.zhongtian.datascene.auth.annotation.ModelMenu;
import com.zhongtian.datascene.auth.annotation.NavMenu;
import com.zhongtian.datascene.auth.annotation.Res;
import com.zhongtian.datascene.auth.vo.AuthFinalVal;
import com.zhongtian.datascene.auth.web.model.RoleBindModel;
import com.zhongtian.datascene.auth.web.model.RoleEditModel;
import com.zhongtian.datascene.auth.web.model.RoleSearchModel;

@NavMenu(name="角色管理1",href="/list",orderNum=1,psn="auth_root",icon="icon-book")
@Res(name="角色管理",orderNum=1,psn="auth_root",sn="role")
@Controller
@RequestMapping(value = "admin/role")
public class RoleController {  
	
	@ModelMenu
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, @ModelAttribute RoleSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        /*model.addAttribute(searchModelName, searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", roleService.listPage(RoleModelExtension.toRoleSearch(searchModel), pageNo, pageSize));*/
        return "role/list";
    }
	
	@ModelMenu(menuPos=AuthFinalVal.MENU_MODEL_OPER)
	@AuthOper
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(HttpServletRequest request, Model model){	
		/*if(!model.containsAttribute("contentModel"))
		{
			RoleEditModel roleEditModel=new RoleEditModel();
			model.addAttribute("contentModel", roleEditModel);
		}*/
        return "role/edit";	  
	}
	
	@AuthOper
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") RoleEditModel editModel, BindingResult result) {
        /*if(result.hasErrors())
            return add(request, model);
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Role role=RoleModelExtension.toRole(editModel);
        roleService.save(role);
        if(returnUrl==null)
        	returnUrl="role/list";
    	return "redirect:"+returnUrl; */
		return "";
    }
	
	@ModelMenu
	@RequestMapping(value="/bind/{id}", method = {RequestMethod.GET})
	public String bind(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) {
		/*Role role=roleService.get(id);
		List<Integer> checkedAuthorityIds = new ArrayList<Integer>();
		
		if(role.getAuthorities()!=null){	
			List<Authority> roleAuthorities=role.getAuthorities();
			for(Authority item : roleAuthorities){
				checkedAuthorityIds.add(item.getId());
			}			
		}
		
		if(!model.containsAttribute("contentModel")){
			RoleBindModel roleBindModel=RoleBindModelExtension.toRoleBindModel(role);
			Integer[] checkedAuthorityIdsArray=new Integer[checkedAuthorityIds.size()];
			checkedAuthorityIds.toArray(checkedAuthorityIdsArray);
			roleBindModel.setAuthorityIds(checkedAuthorityIdsArray);
			model.addAttribute("contentModel", roleBindModel);
		}
		
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		List<TreeModel> children=TreeModelExtension.ToTreeModels(authorityService.listChain(), null, checkedAuthorityIds, StringHelper.toIntegerList( expanded, ","));		
		List<TreeModel> treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel(null,null,"根节点",false,false,false,children)));
		model.addAttribute(treeDataSourceName, JSONArray .fromObject(treeModels, new JsonConfig()).toString());*/

		return "role/bind";
	}
	
	@RequestMapping(value="/bind/{id}", method = {RequestMethod.POST})
	public String bind(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") RoleBindModel roleBindModel, @PathVariable(value="id") Integer id, BindingResult result) {
        /*if(result.hasErrors())
            return bind(request, model, id);
        
        roleService.saveAuthorize(id, ArrayHelper.removeArrayItem(roleBindModel.getAuthorityIds(), new Integer(0)));      
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="role/list";
    	return "redirect:"+returnUrl; */
		return "";
	}   
}  
