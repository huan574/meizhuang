package com.meizhuang.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

public class VerificationManage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String selectFiled = " t.*,s.app_name appName ";
	
	public static final String fromFiled = " verification_manage t inner JOIN app_product s on s.app_id = t.app_id ";
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "产品ID")
	private Integer appId;
	
	@ApiModelProperty(value = "版本号")
    private Integer verificationType;

	@ApiModelProperty(value = "更新内容")
    private Integer state;
	
	@ApiModelProperty(value = "产品名")
	private transient String appName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getVerificationType() {
		return verificationType;
	}

	public void setVerificationType(Integer verificationType) {
		this.verificationType = verificationType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	
	
}
