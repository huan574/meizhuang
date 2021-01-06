package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class Template implements Serializable{

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
	
	@ApiModelProperty(value = "状态  1.可用  2.禁用")
	private Integer state;
	
	@ApiModelProperty(value = "最小张数",required=true)
	@NotNull
	private Integer minNum;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;
	
	@ApiModelProperty(value = "顺序",required=true)
	private transient Integer order;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
	
}
