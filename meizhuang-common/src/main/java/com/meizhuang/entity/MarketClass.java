package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "市场分类")
public class MarketClass implements Serializable{

	public static final String selectFiled = " m1.*, m2.class_name as pClassName, m3.class_name as pClassName2 ";
	
	public static final String fromFiled = " market_class m1 left join market_class m2 on(m1.p_id = m2.id) left join market_class m3 on(m2.p_id = m3.id)";
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private String className;
	
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;
	
	private Integer type;
	
	private Integer pId;
	
	private transient String pClassName;
	
	private transient String pClassName2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpClassName() {
		return pClassName;
	}

	public void setpClassName(String pClassName) {
		this.pClassName = pClassName;
	}

	public String getpClassName2() {
		return pClassName2;
	}

	public void setpClassName2(String pClassName2) {
		this.pClassName2 = pClassName2;
	}
	
	
	
	
	
}
