package com.meizhuang.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

public class SectionUi implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "UI名称",required=true)
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "UI跳转链接",required=true)
	@NotEmpty
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
