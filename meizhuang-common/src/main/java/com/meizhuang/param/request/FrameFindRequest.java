package com.meizhuang.param.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class FrameFindRequest implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "appId")
	private Integer appId;
	
	@ApiModelProperty(value = "APP模板")
	private Integer appMode;
	
	@ApiModelProperty(value = "框架id")
	private Integer id;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getAppMode() {
		return appMode;
	}

	public void setAppMode(Integer appMode) {
		this.appMode = appMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
