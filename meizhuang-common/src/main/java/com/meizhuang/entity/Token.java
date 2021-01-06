package com.meizhuang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

@ApiModel(description = "登录秘钥信息")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "用户ID")
	private Integer uid;

	@ApiModelProperty(value = "秘钥")
	private String token;

	@ApiModelProperty(value = "创建时间")
	private Date ctime;

	@ApiModelProperty(value = "更新时间")
	private Date mtime;
	
	@ApiModelProperty(value = "登陆成功时间差")
	private transient Integer loginMinuteTime;
	
	
	

	public Integer getLoginMinuteTime() {
		return loginMinuteTime;
	}

	public void setLoginMinuteTime(Integer loginMinuteTime) {
		this.loginMinuteTime = loginMinuteTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
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

	public static class Builder {
		private Token obj;

		public Builder() {
			this.obj = new Token();
		}

		public Builder id(Long id) {
			obj.id = id;
			return this;
		}

		public Builder uid(Integer uid) {
			obj.uid = uid;
			return this;
		}

		public Builder token(String token) {
			obj.token = token;
			return this;
		}

		public Builder ctime(Date ctime) {
			obj.ctime = ctime;
			return this;
		}

		public Builder mtime(Date mtime) {
			obj.mtime = mtime;
			return this;
		}

		public Token build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", uid=" + uid + ", token=" + token
				+ ", ctime=" + ctime + ", mtime=" + mtime + "]";
	}
}