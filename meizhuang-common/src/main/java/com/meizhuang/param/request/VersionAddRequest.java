package com.meizhuang.param.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "添加版本请求参数")
public class VersionAddRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "版本号", required = true)
	@NotBlank(message = "版本号不能为空")
    private String version;
    
	@ApiModelProperty(value = "更新内容", required = true)
	@NotBlank(message = "更新内容不能为空")
    private String contentEn;
	
	@ApiModelProperty(value = "下载地址", required = true)
	@NotBlank(message = "下载地址不能为空")
    private String downloadUrl;
	
	@ApiModelProperty(value = "产品id")
	@NotNull(message = "产品不能为空")
    private Integer appId;
	
    @ApiModelProperty(value = "是否需要强制升级(0否、1是)")
    private Byte forceUpdate;

    @ApiModelProperty(value = "操作系统(android、ios)", required = true)
    @NotBlank(message = "操作系统不能为空")
    private String osName;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Byte getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Byte forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	

}
