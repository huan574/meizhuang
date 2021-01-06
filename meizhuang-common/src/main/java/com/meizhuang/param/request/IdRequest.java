package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@ApiModel(description = "通用请求参数")
public class IdRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键id", required = true)
	@NotNull(message = "主键id不能为空")
	private Integer id;

	public IdRequest() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}
