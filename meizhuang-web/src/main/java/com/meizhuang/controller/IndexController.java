package com.meizhuang.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.meizhuang.aspect.SystemOperationLog;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.enums.SystemOperationType;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.OfferProductService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IndexController extends BaseController {

	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("index/index");
	}

	@RequestMapping(value = "/indexdefault")
	public ModelAndView indexdefault() {
		return new ModelAndView("index/indexdefault");
	}

	@RequestMapping(value = "/power")
	public ModelAndView power() {
		return new ModelAndView("error/power");
	}

	// 用户退出
	@RequestMapping(value = "/logout")
	@SystemOperationLog(value = "退出登录", operationType = SystemOperationType.UPDATE)
	public JsonResult<Object> logout() {
		super.getSession().invalidate();
		return JsonResult.buildSuccess("操作成功");
	}

	@RequestMapping(value = "/initDate")
	public JsonResult<String> initDate() {
		
		return JsonResult.buildSuccess("操作成功");
	}
	
	/**
	 * 刷新资源权限
	 * 
	 * @param request
	 * @param response
	 
	@RequestMapping(value = "/refreshResourceAuthority")
	@SystemOperationLog(value = "刷新资源权限", operationType = SystemOperationType.UPDATE)
	public JsonResult<Object> refreshResourceAuthority() {
		AdminSession adminSession = (AdminSession) super.getSession().getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID);
		Authority.clearCache(String.valueOf(adminSession.getId()));
		List<Menu> menus = Authority.findMenuLists(adminSession.getId());
		
		
		
		for (Menu menu : menus) {
			if (StringUtils.isEmpty(menu.getIcon())) {
				menu.setIcon(IconClass.getIcon());
			}
		}
		super.getSession().setAttribute("menus", menus);
		return JsonResult.buildSuccess("刷新成功");
	}*/

}
