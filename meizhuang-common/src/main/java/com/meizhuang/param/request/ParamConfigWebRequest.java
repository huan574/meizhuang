package com.meizhuang.param.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ParamConfigWebRequest implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "参数名", required = true)
	@NotNull(message = "参数名不能为空")
    private String name;
	
	@ApiModelProperty(value = "描述", required = true)
	@NotNull(message = "描述不能为空")
    private String fieldName;
	
	@ApiModelProperty(value = "参数值", required = true)
	@NotNull(message = "参数值不能为空")
    private String value;
	
	@ApiModelProperty(value = "产品编号")
    private Integer appId;
	
	@ApiModelProperty(value = "代理商id")
    private Integer agentId;
	
	@ApiModelProperty(value = "详细说明")
    private String remake;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}
	
	
}