package com.meizhuang.sms.enums;

public enum TunnelsEnum {

	SZ_SMS_CMCC("移动短信通道", "SZ_SMS_CMCC"), //
	NEXMO_SMS("NEXMO国际通讯短信通道", "NEXMO_SMS");

	private String name;

	private String number;

	TunnelsEnum(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
