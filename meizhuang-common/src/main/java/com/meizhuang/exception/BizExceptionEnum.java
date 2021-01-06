package com.meizhuang.exception;

/**
 * 所有业务异常的枚举
 */
public enum BizExceptionEnum {

	// 400 参数错误
	// 401 验证错误

	/**
	 * 账户问题
	 */
	USER_ALREADY_REG(401, "该用户已经注册"), //
	USER_NOT_EXISTED(401, "没有此用户"), //
	USEREXT_NOT_EXISTED(401, "没有此用户扩展信息"), //
	AGENT_NOT_EXISTED(401, "代理商不存在"), //
	ACCOUNT_FREEZED(401, "账号被冻结"), //

	/**
	 * 资金账户问题
	 */
	ACCOUNT_NOT_EXISTED(401, "资金账户不存在"), //
	ACCOUNT_BALANCE_INSUFFICIENT(402, "账户余额不足"), //
	ACCOUNT_BALANCE_EXCEPTION(403, "资金账户信息异常"), //
	ACCOUNT_BALANCE_UPDATE(404, "资金账户余额修改异常"), //
	ACCOUNT_DEPOSIT_BALANCE_UPDATE(404, "资金账户代币修改异常"), //
	ACCOUNT_CREATE(405, "创建账户失败"), //
	ACCOUNT_EXCEPTION(406, "资金账户异常"), //
	ACCOUNT_TRANSFER_REFOUND(407, "退款失败"), //

	/**
	 * 订单记录问题
	 */
	TRANSACTIN_RECORD_UPDATE(401, "订单记录修改异常"), //
	
	/**
	 * 订单记录问题
	 */
	DEPOSIT_RECORD_UPDATE(401, "XDB记录修改异常"), //
	
	/**
	 * 订单记录问题
	 */
	DEPOSIT_CANCEL_UPDATE(402, "订单取消异常"), //
	
	/***
	 * 授信金额，代币金额添加异常
	 */
	CREDIT_AMOUNT_ADD(401,"佣金转代币异常"), //
	
	/**
	 * 退币申请异常
	 */
	REFUND_XDB_UPDATE(401, "卖币申请异常"), //
	
	/***
	 *  补贴订单异常
	 */
	SUBSIDY_ORDER_INSERT(401, "补贴订单申请异常"), //
	SUBSIDY_ORDER_UPDATE(402, "补贴订单审核异常"), //
	
	/**
	 * 系统参数问题
	 */
	PARAM_CONFIG_EXCEPTION(401, "系统参数未配置"), //

	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400, "文件读取错误"), //
	FILE_NOT_FOUND(400, "文件不存在"), //
	FILE_SAVE_ERROR(400, "文件保存失败"), //
	FILE_MAX_SIZE_ERROR(400, "文件大小超出限制, 请压缩或降低文件质量"), //
	FILE_IMAGE_FORMAT_ERROR(400, "请上传图片格式的文件"), //

	/**
	 * 请求错误
	 */
	ILLEGAL_REQUESTS(400, "非法请求"), //
	REPEATED_REQUESTS(400, "重复请求"), //
	REQUESTS_RETURN_EMPTY(400, "未查询到符合条件的数据"), //

	/**
	 * 参数错误
	 */
	PARAM_NULL(400, "参数为空"), //
	PARAM_ERROR(400, "参数错误"), //
	PARAM_FORMAT_ERROR(400, "参数格式错误"), //

	/**
	 * 登录和权限验证
	 */
	NO_OPERATION_POWER(999, "没有操作权限哦，快去申请权限吧~"), //
	TOKEN_ERROR(999, "token验证失败"), //
	SESSION_TIMEOUT(999, "登录超时, 请重新登录"), //
	CHANGE_MOBILE(999, "更换设备，重新登录"),

	/**
	 * 服务器错误
	 */
	SERVER_ERROR(500, "出错啦，请稍候重试哦~"), // 系统出现异常
	BAD_GATEWAY(502, "服务器不理我啦~"), // 收到了上游服务器的无效响应
	SERVICE_UNAVAILABLE(503, "服务器挂了啦~"), // 临时的服务器维护/过载
	GATEWAY_TIMEOUT(504, "服务器变成蜗牛啦~"),// 请求超时
	CONCURRENT_REQUESTS(505, "操作过快，请休息一会~"),// 操作过快，请休息一会~
	
	
	/**
	 * 下发
	 */
	TRANSFER_REPAYMENT_ADD_EXCEPTION(601, "产生还款订单失败"),
	TRANSFER_REPAYMENT_UPDATE_EXCEPTION(602, "更新下发订单还款订单失败"),
	
	
	
	/**
	 * 级别
	 */
	LEVEL_NOT_EXISTED(701, "查询的级别不存在"); //
	

	BizExceptionEnum(int code, String message) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
	}

	BizExceptionEnum(int code, String message, String urlPath) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
		this.urlPath = urlPath;
	}

	private int friendlyCode;

	private String friendlyMsg;

	private String urlPath;

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
