package com.meizhuang.result;

public enum ResultEnum {

	/** 操作成功 */
	COMMON_OPERATE_SUCCESS(0, "操作成功"),
	/** 操作失败 */
	COMMON_OPERATE_FAIL(0, "操作失败"),
	/** 查询成功 */
	COMMON_SELECT_SUCCESS(0, "查询成功"),
	/** 查询失败 */
	COMMON_SELECT_FAIL(0, "查询失败"),
	/** 添加成功 */
	COMMON_ADD_SUCCESS(0, "添加成功"),
	/** 添加失败 */
	COMMON_ADD_FAIL(0, "添加失败"),
	/** 修改成功 */
	COMMON_UPDATE_SUCCESS(0, "修改成功"),
	/** 修改失败 */
	COMMON_UPDATE_FAIL(0, "修改失败"),
	/** 删除成功 */
	COMMON_DELETE_SUCCESS(0, "删除成功"),
	/** 删除失败 */
	COMMON_DELETE_FAIL(0, "删除失败");

	private int code;
	private String msg;

	ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
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

}
