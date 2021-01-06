package com.meizhuang.sms.result;

import java.io.Serializable;

public class SmsResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message;
	private String data;

	public SmsResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public SmsResult(boolean success, String message, String data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SmsResult [success=" + success + ", message=" + message + ", data=" + data + "]";
	}

}
