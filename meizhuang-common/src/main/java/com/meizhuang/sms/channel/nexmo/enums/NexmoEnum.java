package com.meizhuang.sms.channel.nexmo.enums;

public enum NexmoEnum {

	NEXMO_COMMON("585ef220", "e3ebe49ee701d0d5", "NEXMO国际通讯短信");

	private String key;
	private String secret;
	private String desc;

	NexmoEnum(String key, String secret, String desc) {
		this.key = key;
		this.secret = secret;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
