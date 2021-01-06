package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;

public class UserConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String selectFiled = "uc.*,u.agent_id";
	public static final String tableFiled = "user_config uc INNER JOIN user u ON uc.uid=u.uid";

	@TableId(type = IdType.INPUT)
	private Integer uid;

	private transient Integer agentId;

	private Byte grabStatus;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date grabOpenTime;

	private Integer grabTimeout;

	private Integer grabMergeCount;

	private transient Integer accountType; // 收款账户类型
	private transient Integer qrcodeType; // 二维码类型
	private transient Integer qrcodeCount; // 二维码数量
	private transient Integer weixinFixedCount; // 微信固定码数量
	private transient Integer weixinGlobalCount;// 微信通用码数量
	private transient Integer alipayFixedCount; // 支付宝固定码数量
	private transient Integer alipayGlobalCount;// 支付宝通用码数量
	private transient Integer yunsanfuFixedCount; // 云闪付固定码数量
	private transient Integer yunsanfuGlobalCount;// 云闪付通用码数量
	private transient Integer jdFixedCount; // 京东固定码数量
	private transient Integer jdGlobalCount;// 京东通用码数量
	private transient Integer qqFixedCount; // QQ固定码数量
	private transient Integer qqGlobalCount;// QQ通用码数量
	private transient Integer bankCardFixedCount; // 银行卡固定码数量
	private transient Integer bankCardGlobalCount;// 银行卡通用码数量
	
	
	

	public Integer getJdFixedCount() {
		return jdFixedCount;
	}

	public void setJdFixedCount(Integer jdFixedCount) {
		this.jdFixedCount = jdFixedCount;
	}

	public Integer getJdGlobalCount() {
		return jdGlobalCount;
	}

	public void setJdGlobalCount(Integer jdGlobalCount) {
		this.jdGlobalCount = jdGlobalCount;
	}

	public Integer getQqFixedCount() {
		return qqFixedCount;
	}

	public void setQqFixedCount(Integer qqFixedCount) {
		this.qqFixedCount = qqFixedCount;
	}

	public Integer getQqGlobalCount() {
		return qqGlobalCount;
	}

	public void setQqGlobalCount(Integer qqGlobalCount) {
		this.qqGlobalCount = qqGlobalCount;
	}

	public Integer getBankCardFixedCount() {
		return bankCardFixedCount;
	}

	public void setBankCardFixedCount(Integer bankCardFixedCount) {
		this.bankCardFixedCount = bankCardFixedCount;
	}

	public Integer getBankCardGlobalCount() {
		return bankCardGlobalCount;
	}

	public void setBankCardGlobalCount(Integer bankCardGlobalCount) {
		this.bankCardGlobalCount = bankCardGlobalCount;
	}

	public Integer getYunsanfuFixedCount() {
		return yunsanfuFixedCount;
	}

	public void setYunsanfuFixedCount(Integer yunsanfuFixedCount) {
		this.yunsanfuFixedCount = yunsanfuFixedCount;
	}

	public Integer getYunsanfuGlobalCount() {
		return yunsanfuGlobalCount;
	}

	public void setYunsanfuGlobalCount(Integer yunsanfuGlobalCount) {
		this.yunsanfuGlobalCount = yunsanfuGlobalCount;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Byte getGrabStatus() {
		return grabStatus;
	}

	public void setGrabStatus(Byte grabStatus) {
		this.grabStatus = grabStatus;
	}

	public Date getGrabOpenTime() {
		return grabOpenTime;
	}

	public void setGrabOpenTime(Date grabOpenTime) {
		this.grabOpenTime = grabOpenTime;
	}

	public Integer getGrabTimeout() {
		return grabTimeout;
	}

	public void setGrabTimeout(Integer grabTimeout) {
		this.grabTimeout = grabTimeout;
	}

	public Integer getGrabMergeCount() {
		return grabMergeCount;
	}

	public void setGrabMergeCount(Integer grabMergeCount) {
		this.grabMergeCount = grabMergeCount;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(Integer qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	public Integer getQrcodeCount() {
		return qrcodeCount;
	}

	public void setQrcodeCount(Integer qrcodeCount) {
		this.qrcodeCount = qrcodeCount;
	}

	public Integer getWeixinFixedCount() {
		return weixinFixedCount;
	}

	public void setWeixinFixedCount(Integer weixinFixedCount) {
		this.weixinFixedCount = weixinFixedCount;
	}

	public Integer getWeixinGlobalCount() {
		return weixinGlobalCount;
	}

	public void setWeixinGlobalCount(Integer weixinGlobalCount) {
		this.weixinGlobalCount = weixinGlobalCount;
	}

	public Integer getAlipayFixedCount() {
		return alipayFixedCount;
	}

	public void setAlipayFixedCount(Integer alipayFixedCount) {
		this.alipayFixedCount = alipayFixedCount;
	}

	public Integer getAlipayGlobalCount() {
		return alipayGlobalCount;
	}

	public void setAlipayGlobalCount(Integer alipayGlobalCount) {
		this.alipayGlobalCount = alipayGlobalCount;
	}

	public static class Builder {
		private UserConfig obj;

		public Builder() {
			this.obj = new UserConfig();
		}

		public Builder uid(Integer uid) {
			obj.uid = uid;
			return this;
		}

		public Builder grabStatus(Byte grabStatus) {
			obj.grabStatus = grabStatus;
			return this;
		}

		public Builder grabOpenTime(Date grabOpenTime) {
			obj.grabOpenTime = grabOpenTime;
			return this;
		}

		public Builder grabTimeout(Integer grabTimeout) {
			obj.grabTimeout = grabTimeout;
			return this;
		}

		public Builder grabMergeCount(Integer grabMergeCount) {
			obj.grabMergeCount = grabMergeCount;
			return this;
		}

		public UserConfig build() {
			return this.obj;
		}
	}

}