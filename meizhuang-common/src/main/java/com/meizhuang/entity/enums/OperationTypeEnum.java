package com.meizhuang.entity.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public enum OperationTypeEnum {

	/**
	 * 登录
	 */
	LOGIN("login", "登录"),
	/**
	 * 退出登录
	 */
	LOGOUT("logout", "退出登录"),
	/**
	 * 修改登录密码
	 */
	LOGIN_PASSWORD_UPDATE("login_password_update", "修改登录密码"),
	/**
	 * 重置登录密码
	 */
	LOGIN_PASSWORD_RESET("login_password_reset", "重置登录密码"),
	/**
	 * 修改手机号
	 */
	MOBILE_PHONE_UPDATE("mobile_phone_update", "修改手机号"),

	/**
	 * 设置交易密码
	 */
	TRADE_PASSWORD_SET("trade_password_set", "设置交易密码"),
	/**
	 * 启用禁用
	 */
	ENABLE_DISABLED("enable_disabled", "启用禁用用户状态"),
	/**
	 * 修改交易密码
	 */
	TRADE_PASSWORD_UPDATE("trade_password_update", "修改交易密码"),
	/**
	 * 重置交易密码
	 */
	TRADE_PASSWORD_RESET("trade_password_reset", "重置交易密码"),

	/**
	 * 修改二维码
	 */
	QRCODE_UPDATE("qrcode_update", "修改二维码"),
	/**
	 * 修改二维码
	 */
	QRCODE_DELETE("qrcode_delete", "删除二维码"),
	/**
	 * 修改二维码
	 */
	QRCODE_CLEAR("qrcode_clear", "清空二维码"),
	/**
	 * 确认收款
	 */
	CONFIRM_RECEIPT("confirm_receipt", "确认收款"),
	
	/**
	 * 订单申诉
	 */
	ORDER_SHENSU("order_shensu", "订单申诉"),
	/**
	 * 删除收款账户
	 */
	Receive_Account_DELETE("receive_account_delete", "删除收款账号"),
	/**
	 * 提交充值
	 */
	DO_DEPOSIT_OFFLINE("do_deposit_offline", "提交充值"),
	/**
	 * 上传充值凭证
	 */
	UPLOAD_DEPOSIT_OFFLINE("upload_deposit_offline", "上传充值凭证"),
	/**
	 * 取消充值
	 */
	CANCEL_DEPOSIT_OFFLINE("cancel_deposit_offline", "取消充值"),
	/**
	 * 删除充值
	 */
	DELETE_DEPOSIT_OFFLINE("delete_deposit_offline", "删除充值"),
	/**
	 * 审核充值
	 */
	AUDIT_DEPOSIT_OFFLINE("audit_deposit_offline", "审核充值"),
	/**
	 * 充值确认到账
	 */
	CONFIRM_DEPOSIT_OFFLINE("confirm_deposit_offline", "充值确认到账"),
	/**
	 * 用户授信
	 */
	USER_CREDIT("user_credit", "用户授信"),
	/**
	 * 开启/关闭授信
	 */
	USER_CREDIT_STATUS("user_credit_status", "启/关闭授信"),
	/**
	 * 提交提现
	 */
	DO_WITHDRAW("do_withdraw", "提交提现"),
	/**
	 * 取消提现
	 */
	CANCEL_WITHDRAW("cancel_withdraw", "取消提现"),
	/**
	 * 审核提现
	 */
	AUDIT_WITHDRAW("audit_withdraw", "审核提现"),
	/**
	 * 提现确认打款
	 */
	CONFIRM_WITHDRAW("confirm_withdraw", "提现确认打款"),
	/**
	 * 还款提交
	 */
	CONFIM_REPAYMENT("confim_repayment", "提交还款"),
	/**
	 * 还款审核
	 */
	AUDIT_REPAYMENT("audit_repayment", "还款审核"),
	/**
	 * 确认还款
	 */
	CONFIRM_REPAYMENT("confirm_repayment", "确认还款"),
	/**
	 * 确认到账
	 */
	CONFIRM_ACCOUNT("confirm_account", "确认到账"),
	
	/**
	 * 开启自动抢单
	 */
	OPEN_AUTOMERGE("open_automerge", "开启自动抢单"),
	
	/**
	 * 关闭自动抢单
	 */
	CANCEL_AUTOMERGE("cancel_automerge", "关闭自动抢单"),
	
	
	/**
	 * 一键补单
	 */
	ONE_KEY_REPAIR_ORDER("one_key_repair_order", "一键补单"),
	
	/**
	 * 会员自动升级
	 */
	USER_LEVEL_IS_UPDARE("user_level_is_update", "会员自动升级"),
	
	;

	private String type;
	private String desc;

	OperationTypeEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDescByType(String type) {
		if (StringUtils.isNotEmpty(type)) {
			OperationTypeEnum[] operationTypes = OperationTypeEnum.values();
			for (OperationTypeEnum operationType : operationTypes) {
				if (operationType.getType().equals(type)) {
					return operationType.getDesc();
				}
			}
		}
		return null;
	}

	public static List<OperationTypeEnum.OperationType> getOperationTypes() {
		List<OperationTypeEnum.OperationType> list = new ArrayList<OperationTypeEnum.OperationType>();
		OperationTypeEnum[] enums = OperationTypeEnum.values();
		for (OperationTypeEnum operationTypeEnum : enums) {
			list.add(operationTypeEnum.new OperationType(operationTypeEnum.getType(), operationTypeEnum.getDesc()));
		}
		return list;
	}

	@ApiModel(description = "操作类型返回参数")
	public class OperationType {

		@ApiModelProperty(value = "操作类型")
		private String type;
		@ApiModelProperty(value = "操作描述")
		private String desc;

		private OperationType(String type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

}
