package com.meizhuang.sms.channel.cmcc.enums;

public enum ReturnCode {

	CMCC_200(200, "短信发送成功"), //
	CMCC_301(301, "账号或密码错误"), //
	CMCC_302(302, "账号已停用"), //
	CMCC_303(303, "余额不足"), //
	CMCC_304(304, "手机号码为空或者超过最大发送数量"), //
	CMCC_305(305, "数据认证错误"), //
	CMCC_306(306, "短信内容为空或超长"), //
	CMCC_307(307, "扩展码不正确"), //
	CMCC_308(308, "批次号过长"), //
	CMCC_309(309, "含有敏感词"), //
	CMCC_998(998, "响应超时"), //
	CMCC_999(999, "其他未知错误");

	private Integer code;
	private String msg;

	private ReturnCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String getMsgByCode(String code) {
		ReturnCode[] enums = ReturnCode.values();
		for (ReturnCode returnCode : enums) {
			if (returnCode.getCode().toString().equals(code)) {
				return returnCode.getMsg();
			}
		}
		return code;
	}

}
