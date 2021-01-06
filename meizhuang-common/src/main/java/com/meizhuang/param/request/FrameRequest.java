package com.meizhuang.param.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "模板请求参数")
public class FrameRequest implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "模板名称")
	private String name;
	
	@ApiModelProperty(value = "风格  1.通用  2.定制")
	private Integer style;
	
	
	@ApiModelProperty(value = "app模块  1.首页  2.我的 3.消息 4.订单 5.活动")
	private Integer appMode;
	
	@ApiModelProperty(value = "状态 1.可用 2.禁用")
	private Integer state;
	
	@ApiModelProperty(value = "产品id")
	private Integer appId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getAppMode() {
		return appMode;
	}

	public void setAppMode(Integer appMode) {
		this.appMode = appMode;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	
	
}
