package com.meizhuang.param.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "根据域名获取产品编号接口响应参数")
public class AppProductInfoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "产品编号")
	private Integer appId;
	
	@ApiModelProperty(value = "产品图标")
	private String appIcon;
	
	@ApiModelProperty(value = "产品名称")
	private String appName;
	
	
	private String interfaceHost;
	
	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getInterfaceHost() {
		return interfaceHost;
	}

	public void setInterfaceHost(String interfaceHost) {
		this.interfaceHost = interfaceHost;
	}

	@Override
	public String toString() {
		return "AppProductResponse [appId=" + appId + ", appIcon=" + appIcon + "]";
	}

}
