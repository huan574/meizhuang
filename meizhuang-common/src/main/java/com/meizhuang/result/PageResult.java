package com.meizhuang.result;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.meizhuang.exception.BizExceptionEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "分页响应返回对象")
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返回状态")
	private boolean success; // 返回状态

	@ApiModelProperty(value = "返回码")
	private int code; // 返回码

	@ApiModelProperty(value = "返回描述")
	private String msg; // 返回描述

	@ApiModelProperty(value = "返回总记录数")
	private Long count; // 总记录数

	@ApiModelProperty(value = "返回分页数据")
	private List<T> data; // 列表数据

	private T footer; // 列表统计底部数据

	@ApiModelProperty(value = "返回自定义附加数据")
	private Object object; // 自定义附加数据

	private PageResult(boolean success, int code, String msg, Long count, List<T> data, T footer, Object object) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
		this.footer = footer;
		this.object = object;
	}

	private static <T> PageResult<T> build(boolean success, int code, String msg, Long count, List<T> data, T footer, Object object) {
		return new PageResult<T>(success, code, msg, count, data, footer, object);
	}

	@SuppressWarnings("unchecked")
	public static <T> PageResult<T> buildSuccess() {
		return build(true, 0, "成功", 0L, Collections.EMPTY_LIST, null, null);
	}

	public static <T> PageResult<T> buildSuccess(Long count, List<T> data) {
		return build(true, 0, "成功", count, data, null, null);
	}

	public static <T> PageResult<T> buildSuccess(Long count, List<T> data, T footer) {
		return build(true, 0, "成功", count, data, footer, null);
	}

	public static <T> PageResult<T> buildSuccess(Long count, List<T> data, T footer, Object object) {
		return build(true, 0, "成功", count, data, footer, object);
	}

	public static <T> PageResult<T> buildError(String msg) {
		return build(false, 0, msg, 0L, null, null, null);
	}

	public static <T> PageResult<T> buildError(int code, String msg) {
		return build(false, code, msg, 0L, null, null, null);
	}

	public static <T> PageResult<T> buildError(BizExceptionEnum bizExceptionEnum) {
		return build(false, bizExceptionEnum.getCode(), bizExceptionEnum.getMessage(), 0L, null, null, null);
	}

	public static <T> PageResult<T> buildError(ResultEnum resultEnum) {
		return build(false, resultEnum.getCode(), resultEnum.getMsg(), 0L, null, null, null);
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public T getFooter() {
		return footer;
	}

	public void setFooter(T footer) {
		this.footer = footer;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "PageResult [success=" + success + ", code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + ", footer=" + footer + ", object=" + object + "]";
	}

}
