package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.meizhuang.entity.enums.SystemOperationType;

import cn.hutool.core.date.DatePattern;

public class SystemLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	private String operationType;
	private transient String operationTypeDesc;

	private String content;

	private String url;

	private String method;

	private String params;

	private String returnValue;

	private Long runTime;

	private String ip;

	private String cman;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType == null ? null : operationType.trim();
	}

	public String getOperationTypeDesc() {
		operationTypeDesc = SystemOperationType.getDescByValue(operationType);
		return operationTypeDesc;
	}

	public void setOperationTypeDesc(String operationTypeDesc) {
		this.operationTypeDesc = operationTypeDesc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getCman() {
		return cman;
	}

	public void setCman(String cman) {
		this.cman = cman == null ? null : cman.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public static class Builder {
		private SystemLog obj;

		public Builder() {
			this.obj = new SystemLog();
		}

		public Builder id(Long id) {
			obj.id = id;
			return this;
		}

		public Builder operationType(String operationType) {
			obj.operationType = operationType;
			return this;
		}

		public Builder content(String content) {
			obj.content = content;
			return this;
		}

		public Builder url(String url) {
			obj.url = url;
			return this;
		}

		public Builder method(String method) {
			obj.method = method;
			return this;
		}

		public Builder params(String params) {
			obj.params = params;
			return this;
		}

		public Builder returnValue(String returnValue) {
			obj.returnValue = returnValue;
			return this;
		}

		public Builder runTime(Long runTime) {
			obj.runTime = runTime;
			return this;
		}

		public Builder ip(String ip) {
			obj.ip = ip;
			return this;
		}

		public Builder cman(String cman) {
			obj.cman = cman;
			return this;
		}

		public Builder ctime(Date ctime) {
			obj.ctime = ctime;
			return this;
		}

		public SystemLog build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", operationType=" + operationType + ", content=" + content + ", url=" + url + ", method=" + method + ", params=" + params + ", returnValue=" + returnValue + ", runTime=" + runTime + ", ip=" + ip + ", cman=" + cman + ", ctime=" + ctime + "]";
	}

}