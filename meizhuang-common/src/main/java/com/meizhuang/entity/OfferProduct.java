package com.meizhuang.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class OfferProduct implements Serializable {
	
	
	public static final String selectFiled = " o.*,u.mobile,u.weixin,u.weixin_img,u.store_name,u.store_remark,u.trade_stall,u.random ";
	
	public static final String fromFiled = " offer_product o inner JOIN user_info u on o.uid = u.uid ";
		
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String offerProduct;
    
    private String product;

    private BigDecimal gatherPrice;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;
    
    private transient String mobile;
    
    private transient String weixin;
    
    private transient String weixinImg;
    
    private transient String storeName;
    
    private transient String storeRemark;
    
    private transient String tradeStall;
    
    private transient String random;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOfferProduct() {
        return offerProduct;
    }

    public void setOfferProduct(String offerProduct) {
        this.offerProduct = offerProduct == null ? null : offerProduct.trim();
    }
    
    public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getGatherPrice() {
        return gatherPrice;
    }

    public void setGatherPrice(BigDecimal gatherPrice) {
        this.gatherPrice = gatherPrice;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

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

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}




	public static class Builder {
        private OfferProduct obj;

        public Builder() {
            this.obj = new OfferProduct();
        }

        public Builder id(Integer id) {
            obj.id = id;
            return this;
        }

        public Builder uid(Integer uid) {
            obj.uid = uid;
            return this;
        }

        public Builder offerProduct(String offerProduct) {
            obj.offerProduct = offerProduct;
            return this;
        }

        public Builder gatherPrice(BigDecimal gatherPrice) {
            obj.gatherPrice = gatherPrice;
            return this;
        }

        public Builder ctime(Date ctime) {
            obj.ctime = ctime;
            return this;
        }

        public Builder mtime(Date mtime) {
            obj.mtime = mtime;
            return this;
        }

        public OfferProduct build() {
            return this.obj;
        }
    }
}