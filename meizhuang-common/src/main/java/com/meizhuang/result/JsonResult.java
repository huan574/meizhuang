package com.meizhuang.result;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meizhuang.exception.BizExceptionEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果
 */
@ApiModel(description = "响应返回对象")
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(JsonResult.class);
	
	@ApiModelProperty(value = "返回状态")
	private boolean success;

	@ApiModelProperty(value = "返回码")
	private int code;

	@ApiModelProperty(value = "返回描述")
	private String msg;

	@ApiModelProperty(value = "返回数据")
	private T data;

	private JsonResult(boolean success, int code, String msg, T data) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private static <T> JsonResult<T> build(boolean success, int code, String msg, T data) {
		return new JsonResult<T>(success, code, msg, data);
	}

	public static <T> JsonResult<T> buildSuccess() {
		return build(true, 0, "成功", null);
	}

	public static <T> JsonResult<T> buildSuccess(String msg) {
		return build(true, 0, msg, null);
	}

	public static <T> JsonResult<T> buildSuccess(T data) {
		return build(true, 0, "成功", data);
	}

	public static <T> JsonResult<T> buildSuccess(String msg, T data) {
		return build(true, 0, msg, data);
	}

	public static <T> JsonResult<T> buildSuccess(ResultEnum resultEnum) {
		return build(true, resultEnum.getCode(), resultEnum.getMsg(), null);
	}

	public static <T> JsonResult<T> buildSuccess(ResultEnum resultEnum, T data) {
		return build(true, resultEnum.getCode(), resultEnum.getMsg(), data);
	}

	public static <T> JsonResult<T> buildError(String msg) {
		log.warn(msg);
		return build(false, 0, msg, null);
	}

	public static <T> JsonResult<T> buildError(int code, String msg) {
		log.warn(code+msg);
		return build(false, code, msg, null);
	}

	public static <T> JsonResult<T> buildError(BizExceptionEnum bizExceptionEnum) {
		log.warn(bizExceptionEnum.toString());
		return build(false, bizExceptionEnum.getCode(), bizExceptionEnum.getMessage(), null);
	}

	public static <T> JsonResult<T> buildError(ResultEnum resultEnum) {
		log.warn(resultEnum.toString());
		return build(false, resultEnum.getCode(), resultEnum.getMsg(), null);
	}

	@SuppressWarnings("rawtypes")
	public static <T> JsonResult<T> buildError(JsonResult result) {
		log.warn(result.toString());
		return build(false, result.getCode(), result.getMsg(), null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}