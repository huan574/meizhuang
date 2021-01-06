package com.meizhuang.config;

import java.util.ArrayList;
import java.util.List;

import com.authority.app.view.Menu;
import com.meizhuang.utils.common.IconClass;

public class WebMenus {

	private static List<Menu> menus = new ArrayList<Menu>();

	static {
		Menu menu;
		List<Menu> children;

		menu = new Menu("系统管理", "#", IconClass.getIcon());
		children = new ArrayList<Menu>();
		children.add(new Menu("系统异常日志", "/flow/sysException/list", IconClass.getIcon()));
		children.add(new Menu("管理员日志", "/flow/systemLog/list", IconClass.getIcon()));
		children.add(new Menu("系统参数配置", "/flow/paramConfig/list", IconClass.getIcon()));
		children.add(new Menu("公告管理", "/flow/proclamation/list", IconClass.getIcon()));
		children.add(new Menu("广告图管理", "/flow/adStation/list", IconClass.getIcon()));//"/flow/adStation/list"
		children.add(new Menu("鉴别管理","/flow/identify/list",IconClass.getIcon()));
		children.add(new Menu("产品库管理","/flow/categoryClass/list",IconClass.getIcon()));
		children.add(new Menu("H5内容管理","/flow/html/htmlLarticleList",IconClass.getIcon()));
		//children.add(new Menu("模板名称管理", "/flow/template/list", IconClass.getIcon()));
		//children.add(new Menu("框架管理", "/flow/frame/list", IconClass.getIcon()));
		//children.add(new Menu("UI跳转管理", "/flow/sectionUI/list", IconClass.getIcon()));
		//children.add(new Menu("版本管理", "/flow/version/list", IconClass.getIcon()));
		//children.add(new Menu("产品管理", "/flow/appProduct/list", IconClass.getIcon()));
		//children.add(new Menu("产品校验管理", "/flow/verificationmanage/list", IconClass.getIcon()));
		menu.setChildren(children);
		menus.add(menu);

		menu = new Menu("商户管理", "#", IconClass.getIcon());
		children = new ArrayList<Menu>();
		children.add(new Menu("商户列表", "/flow/userInfo/list", IconClass.getIcon()));
		children.add(new Menu("商户报价管理", "/flow/offerProduct/list", IconClass.getIcon()));
		children.add(new Menu("市场分类管理", "/flow/marketClass/list", IconClass.getIcon()));
		menu.setChildren(children);
		menus.add(menu);
		
	}

	public static List<Menu> getMenus() {
		return menus;
	}

}
