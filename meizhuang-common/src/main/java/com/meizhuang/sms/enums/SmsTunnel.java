package com.meizhuang.sms.enums;

import java.io.Serializable;

public class SmsTunnel implements Serializable {

	private static final long serialVersionUID = -619419457914619118L;

	private Integer tunnelType;// 通道类型 1001 国内 1002国外
	private String tunnelNum;// 通道编码SZ_SMS_CMCC
	private String account;// 通道账号标识
	private Integer isMultipleSign;// 该通道该账号是否支持多签名 (1001单签名 1002多签名)

	public SmsTunnel(Integer tunnelType, String tunnelNum, String account, Integer isMultipleSign) {
		this.tunnelType = tunnelType;
		this.tunnelNum = tunnelNum;
		this.account = account;
		this.isMultipleSign = isMultipleSign;
	}

	public SmsTunnel() {

	}

	public String getTunnelNum() {
		return tunnelNum;
	}

	public void setTunnelNum(String tunnelNum) {
		this.tunnelNum = tunnelNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getIsMultipleSign() {
		return isMultipleSign;
	}

	public void setIsMultipleSign(Integer isMultipleSign) {
		this.isMultipleSign = isMultipleSign;
	}

	public Integer getTunnelType() {
		return tunnelType;
	}

	public void setTunnelType(Integer tunnelType) {
		this.tunnelType = tunnelType;
	}

}
