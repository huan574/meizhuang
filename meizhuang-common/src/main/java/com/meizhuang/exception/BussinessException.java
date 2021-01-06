package com.meizhuang.exception;

import com.meizhuang.result.JsonResult;

/**
 * 业务异常的封装
 */
public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 友好提示的code码
	private int friendlyCode;
	// 友好提示
	private String friendlyMsg;
	// 业务异常跳转的页面
	private String urlPath;

	public BussinessException(BizExceptionEnum bizExceptionEnum) {
		this.friendlyCode = bizExceptionEnum.getCode();
		this.friendlyMsg = bizExceptionEnum.getMessage();
		this.urlPath = bizExceptionEnum.getUrlPath();
	}

	public BussinessException(int friendlyCode, String friendlyMsg) {
		this.friendlyCode = friendlyCode;
		this.friendlyMsg = friendlyMsg;
	}
	
	public BussinessException(@SuppressWarnings("rawtypes") JsonResult result) {
		this.friendlyCode = result.getCode();
		this.friendlyMsg = result.getMsg();
	}

	public int getCode() {
		return friendlyCode;
	}

	public void setCode(int code) {
		this.friendlyCode = code;
	}

	public String getMessage() {
		return friendlyMsg;
	}

	public void setMessage(String message) {
		this.friendlyMsg = message;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
