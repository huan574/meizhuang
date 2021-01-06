package com.meizhuang.entity.enums;

public enum SystemParameterEnum {


	
	HOME_DOMAIN_NAME("HOME_DOMAIN_NAME","域名"),
	
	FILE_SAVE_ROOT_PATH("FILE_SAVE_ROOT_PATH","文件保存根目录"),
	
	SEARCH_HOT_KEYS("SEARCH_HOT_KEYS","主页搜索框下的热门关键字,多个加@"),
	
	AD_STATION_STATUS("AD_STATION_STATUS","滚动图开关 0是开 1是关"),
	
	PROCLAMATION_STATUS("PROCLAMATION_STATUS", "滚动公告开关 0是开 1是关"),
	
	SYSTEM_SITE_NAME("SYSTEM_SITE_NAME" , "站点名称"),
	
	RECOMMEND_GATE_IDS("RECOMMEND_GATE_IDS","推荐档口，填商户uid 多个之间加@"),
	
	WEB_CACHE_CONTROL("WEB_CACHE_CONTROL","前端页面CSS JS缓存开关0缓存 1不缓存"),
	
	SYSTEM_SITE_DESC("SYSTEM_SITE_DESC","站点描述"),
	
	HOME_TIP1_TITLE("HOME_TIP1_TITLE","主页TIP Title"),
	
	/**
	 * 单笔最少提现
	 */
	WITHDRAWMIN("withdraw_min", "单笔最少提现"),
	/**
	 * 单笔最大提现
	 */
	WITHDRAWMAX("withdraw_max", "单笔最大提现");
	
	
	
	private String name;
	private String desc;

	SystemParameterEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
