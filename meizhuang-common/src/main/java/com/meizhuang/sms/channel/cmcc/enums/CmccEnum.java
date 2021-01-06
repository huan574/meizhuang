package com.meizhuang.sms.channel.cmcc.enums;

public enum CmccEnum {

	CMCC_COMMON("szjzkj", "2nCH863j", "均州科技(短信支持多签名)"), //
	CMCC_VOICE_COMMN("sanwdyy", "wb6EM7XR", "语音短信(无多签名)");

	private String account;
	private String password;
	private String desc;

	CmccEnum(String account, String password, String desc) {
		this.account = account;
		this.password = password;
		this.desc = desc;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
