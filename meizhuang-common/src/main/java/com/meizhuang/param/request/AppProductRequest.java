package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "根据域名获取产品编号接口请求参数")
public class AppProductRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "域名地址", required = true)
	@NotBlank(message = "域名地址不能为空")
	private String appDomainUrl;

	public String getAppDomainUrl() {
		return appDomainUrl;
	}

	public void setAppDomainUrl(String appDomainUrl) {
		this.appDomainUrl = appDomainUrl;
	}

	@Override
	public String toString() {
		return "AppProductRequest [appDomainUrl=" + appDomainUrl + "]";
	}

}
