package com.meizhuang.param.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ParamConfigRequest implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@ApiModelProperty(value = "id")
    private String id;
    
	@ApiModelProperty(value = "参数名")
    private String name;
	
	@ApiModelProperty(value = "描述")
    private String fieldName;

	@ApiModelProperty(value = "创建时间")
    private String ctime;
	
	@ApiModelProperty(value = "产品编号")
    private Integer appId;
	
	@ApiModelProperty(value = "代理商id")
    private Integer agentId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
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
	
}