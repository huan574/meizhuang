package com.meizhuang.entity;

import java.io.Serializable;
import java.util.List;

public class OfferProductGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	
	private String random;
	
	private  String mobile;
	    
	private  String weixin;
	    
	private  String weixinImg;
	    
	private  String storeName;
	    
	private  String storeRemark;
	    
	private  String tradeStall;
	
	private  String date;
	
	private List<OfferProduct> productList;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getWeixinImg() {
		return weixinImg;
	}

	public void setWeixinImg(String weixinImg) {
		this.weixinImg = weixinImg;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreRemark() {
		return storeRemark;
	}

	public void setStoreRemark(String storeRemark) {
		this.storeRemark = storeRemark;
	}

	public String getTradeStall() {
		return tradeStall;
	}

	public void setTradeStall(String tradeStall) {
		this.tradeStall = tradeStall;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public List<OfferProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<OfferProduct> productList) {
		this.productList = productList;
	}
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferProductGroup other = (OfferProductGroup) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	
	
	
	

}
