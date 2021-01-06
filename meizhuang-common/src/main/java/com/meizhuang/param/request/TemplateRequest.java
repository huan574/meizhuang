package com.meizhuang.param.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "模板名称请求参数")
public class TemplateRequest implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "模板名称")
	private String name;
	
	@ApiModelProperty(value = "状态 1.可用 2.禁用")
	private Integer state;

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
	
	
}
