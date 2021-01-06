package com.meizhuang.entity.enums;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账户类型枚举
 * 
 * @author caidian
 * 
 */
public enum AccountTypeEnum {

	/**
	 * 余额账户
	 */
	BALANCE_ACCOUNT(1000, "余额账户"),
	/**
	 * 佣金账户
	 */
	COMMISSION_ACCOUNT(1001, "佣金账户"),
	/**
	 * 冻结账户
	 */
	LOCK_ACCOUNT(1002, "冻结账户"),
	/**
	 * 授信账户
	 */
	CREDIT_ACCOUNT(1003, "授信账户"),
	/**
	 * 待还款账户
	 */
	REPAYMENT_ACCOUNT(1004, "还款账户"),
	/**
	   * 代币账户
	 */
	CASH_DEPOSIT(1005, "代币账户");
	
	

	private Integer type;
	private String desc;

	AccountTypeEnum(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static AccountTypeEnum getAccountType(Integer type) {
		AccountTypeEnum[] accountTypeEnums = AccountTypeEnum.values();
		for (AccountTypeEnum accountType : accountTypeEnums) {
			if (accountType.getType().intValue() == type.intValue()) {
				return accountType;
			}
		}
		return null;
	}

	public static String getDescByType(Integer type) {
		AccountTypeEnum[] accountTypeEnums = AccountTypeEnum.values();
		for (AccountTypeEnum accountType : accountTypeEnums) {
			if (accountType.getType().intValue() == type.intValue()) {
				return accountType.getDesc();
			}
		}
		return null;
	}

	public static List<AccountTypeEnum.AccountType> getAccountTypes() {
		List<AccountTypeEnum.AccountType> list = new ArrayList<AccountTypeEnum.AccountType>();
		AccountTypeEnum[] enums = AccountTypeEnum.values();
		for (AccountTypeEnum typeEnum : enums) {
			list.add(typeEnum.new AccountType(typeEnum.getType(), typeEnum.getDesc()));
		}
		return list;
	}

	@ApiModel(description = "账户类型返回参数")
	public class AccountType {

		@ApiModelProperty(value = "账户类型")
		private Integer type;
		@ApiModelProperty(value = "账户类型描述")
		private String desc;

		private AccountType(Integer type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
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
