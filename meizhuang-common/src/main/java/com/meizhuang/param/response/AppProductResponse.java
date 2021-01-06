package com.meizhuang.param.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "版本管理")
public class AppProductResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "是否需要更新 0 不需要 1需要")
    private Integer isUpdate;
	
	@ApiModelProperty(value = "服务器URL")
	private String serverUrl;

	public Integer getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Integer isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	@Override
	public String toString() {
		return "VersionResponse [isUpdate=" + isUpdate + "]";
	}

}