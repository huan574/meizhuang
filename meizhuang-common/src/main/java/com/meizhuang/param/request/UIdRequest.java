package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@ApiModel(description = "通用请求参数")
public class UIdRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户编号", required = true)
	@NotNull(message = "用户编号不能为空")
	private Integer uid;

	public UIdRequest() {

	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
