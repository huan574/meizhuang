package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.meizhuang.entity.enums.OperationTypeEnum;

import cn.hutool.core.date.DatePattern;

public class OperationRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	private String type;
	private transient String typeDesc;

	private String content;

	private Integer uid;

	private String ip;

	private String location;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		typeDesc = OperationTypeEnum.getDescByType(type);
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public static class Builder {
		private OperationRecord obj;

		public Builder() {
			this.obj = new OperationRecord();
		}

		public Builder id(Long id) {
			obj.id = id;
			return this;
		}

		public Builder type(String type) {
			obj.type = type;
			return this;
		}

		public Builder content(String content) {
			obj.content = content;
			return this;
		}

		public Builder uid(Integer uid) {
			obj.uid = uid;
			return this;
		}

		public Builder ip(String ip) {
			obj.ip = ip;
			return this;
		}

		public Builder location(String location) {
			obj.location = location;
			return this;
		}

		public Builder ctime(Date ctime) {
			obj.ctime = ctime;
			return this;
		}

		public OperationRecord build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "OperationRecord [id=" + id + ", type=" + type + ", uid=" + uid + ", ip=" + ip + ", location=" + location + ", ctime=" + ctime + "]";
	}
}