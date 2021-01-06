package com.meizhuang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;

@ApiModel(description = "系统日志信息")
public class SysException implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日志编号")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "所属系统")
	private String useSystem;

	@ApiModelProperty(value = "异常信息")
	private String sysContents;

	@ApiModelProperty(value = "服务器IP")
	private String ipAddress;

	@ApiModelProperty(value = "请求路径")
	private String resourceUrl;

	@ApiModelProperty(value = "添加时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	public SysException() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getUseSystem() {
		return useSystem;
	}

	public void setUseSystem(String useSystem) {
		this.useSystem = useSystem == null ? null : useSystem.trim();
	}

	public String getSysContents() {
		return sysContents;
	}

	public void setSysContents(String sysContents) {
		this.sysContents = sysContents == null ? null : sysContents.trim();
	}

	public static class Builder {
		private SysException obj;

		public Builder() {
			this.obj = new SysException();
		}

		public Builder id(Long id) {
			obj.id = id;
			return this;
		}

		public Builder ipAddress(String ipAddress) {
			obj.ipAddress = ipAddress;
			return this;
		}

		public Builder resourceUrl(String resourceUrl) {
			obj.resourceUrl = resourceUrl;
			return this;
		}

		public Builder ctime(Date ctime) {
			obj.ctime = ctime;
			return this;
		}

		public Builder useSystem(String useSystem) {
			obj.useSystem = useSystem;
			return this;
		}

		public Builder sysContents(String sysContents) {
			obj.sysContents = sysContents;
			return this;
		}

		public SysException build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "SysException [id=" + id + ", ipAddress=" + ipAddress + ", resourceUrl=" + resourceUrl + ", ctime=" + ctime + ", useSystem=" + useSystem + ", sysContents=" + sysContents + "]";
	}
}