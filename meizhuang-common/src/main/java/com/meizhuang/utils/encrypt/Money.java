package com.meizhuang.utils.encrypt;

import java.math.BigDecimal;

public class Money {

	private BigDecimal money;

	private String keyData;

	private String encMoney;
	private String checkCode;
	private String tableKey;

	public Money(BigDecimal money, String keyData) {
		this.money = money;
		this.keyData = keyData;
	}

	public Money(BigDecimal money, String keyData, String encMoney, String checkCode, String tableKey) {
		this.money = money;
		this.keyData = keyData;
		this.encMoney = encMoney;
		this.checkCode = checkCode;
		this.tableKey = tableKey;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getKeyData() {
		return keyData;
	}

	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}

	public String getEncMoney() {
		return encMoney;
	}

	public void setEncMoney(String encMoney) {
		this.encMoney = encMoney;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getTableKey() {
		return tableKey;
	}

	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}

	@Override
	public String toString() {
		return "Money [money=" + money + ", keyData=" + keyData + ", encMoney=" + encMoney + ", checkCode=" + checkCode + ", tableKey=" + tableKey + "]";
	}

}
