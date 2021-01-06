package com.meizhuang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;

@ApiModel(description = "短信发送日志信息")
public class SendSmsLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日志编号")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "短信内容")
	private String msg;

	@ApiModelProperty(value = "发送时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	public SendSmsLog() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? null : msg.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public static class Builder {
		private SendSmsLog obj;

		public Builder() {
			this.obj = new SendSmsLog();
		}

		public Builder id(Long id) {
			obj.id = id;
			return this;
		}

		public Builder mobile(String mobile) {
			obj.mobile = mobile;
			return this;
		}

		public Builder msg(String msg) {
			obj.msg = msg;
			return this;
		}

		public Builder ctime(Date ctime) {
			obj.ctime = ctime;
			return this;
		}

		public SendSmsLog build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "SendSmsLog [id=" + id + ", mobile=" + mobile + ", msg=" + msg + ", ctime=" + ctime + "]";
	}

}