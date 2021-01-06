package com.meizhuang.param.request;

import java.io.Serializable;

public class OfferProductRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mobile;
	
	private String tradeStall;
	
	private  String sctime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTradeStall() {
		return tradeStall;
	}

	public void setTradeStall(String tradeStall) {
		this.tradeStall = tradeStall;
	}

	public String getSctime() {
		return sctime;
	}

	public void setSctime(String sctime) {
		this.sctime = sctime;
	}
	
	
}
