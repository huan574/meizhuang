package com.meizhuang.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户账户明细")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "用户ID")
	private Integer uid;

	@ApiModelProperty(value = "账户类型,1000余额账户，1001佣金总额")
	private Integer type;

	@ApiModelProperty(value = "余额")
	private BigDecimal balance;

	@ApiModelProperty(value = "余额加密字段")
	private String encBalance;

	@ApiModelProperty(value = "checkCode")
	private String checkCode;

	@ApiModelProperty(value = "tableKey")
	private String tableKey;

	@ApiModelProperty(value = "版本号")
	@Version
	private Integer version;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	@TableField(update = "now()")
	private Date mtime;
	
	@ApiModelProperty(value = "可分配的待还款金额")
	private transient BigDecimal  canAssignAmount=new BigDecimal(0);
	
	@ApiModelProperty(value = "昵称")
	private transient String nickname;
	
	
	

	public BigDecimal getCanAssignAmount() {
		return canAssignAmount;
	}

	public void setCanAssignAmount(BigDecimal canAssignAmount) {
		this.canAssignAmount = canAssignAmount;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getEncBalance() {
		return encBalance;
	}

	public void setEncBalance(String encBalance) {
		this.encBalance = encBalance == null ? null : encBalance.trim();
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode == null ? null : checkCode.trim();
	}

	public String getTableKey() {
		return tableKey;
	}

	public void setTableKey(String tableKey) {
		this.tableKey = tableKey == null ? null : tableKey.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		private Account obj;

		public Builder() {
			this.obj = new Account();
		}

		public Builder id(Integer id) {
			obj.id = id;
			return this;
		}

		public Builder uid(Integer uid) {
			obj.uid = uid;
			return this;
		}

		public Builder type(Integer type) {
			obj.type = type;
			return this;
		}

		public Builder balance(BigDecimal balance) {
			obj.balance = balance;
			return this;
		}

		public Builder encBalance(String encBalance) {
			obj.encBalance = encBalance;
			return this;
		}

		public Builder checkCode(String checkCode) {
			obj.checkCode = checkCode;
			return this;
		}

		public Builder tableKey(String tableKey) {
			obj.tableKey = tableKey;
			return this;
		}

		public Builder version(Integer version) {
			obj.version = version;
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

		public Account build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", uid=" + uid + ", type=" + type + ", balance=" + balance + ", encBalance=" + encBalance + ", checkCode=" + checkCode + ", tableKey=" + tableKey + ", version=" + version + ", ctime=" + ctime + ", mtime=" + mtime + "]";
	}
}