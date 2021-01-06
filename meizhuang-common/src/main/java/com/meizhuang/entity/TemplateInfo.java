package com.meizhuang.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

public class TemplateInfo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "模板名称",required=true)
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "示例图",required=true)
	@NotEmpty
	private String exampleImg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExampleImg() {
		return exampleImg;
	}

	public void setExampleImg(String exampleImg) {
		this.exampleImg = exampleImg;
	}
	
	
}
