package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class UserInfo implements Serializable {
	
	public static final String selectFiled = " u.uid,store_name,trade_stall,COUNT(o.ctime) as count, u.product_update_time,u.random ";
	
	public static final String fromFiled = " user_info as u LEFT JOIN offer_product as o on(u.uid = o.uid and u.product_update_time = o.ctime)  ";
		
	
	public static final String selectFiled2 = " u.*, m3.class_name as marketCname, m2.class_name as marketMname, m1.class_name as marketLname, m4.class_name as marketSname";
	
	public static final String fromFiled2 = " user_info as u "
			+ "LEFT JOIN market_class as m1 on(u.market_lid = m1.id) "
			+ "LEFT JOIN market_class as m2 on(m1.p_id = m2.id) "
			+ "LEFT JOIN market_class as m3 on(m2.p_id = m3.id)  "
			+ "LEFT JOIN market_class as m4 on(u.market_sid = m4.id)";

	
	public static final String selectFiled3 = " u.*,m4.class_name as marketSname";
	
	public static final String fromFiled3 = " user_info as u "
			+ "RIGHT JOIN market_class as m4 on(u.market_sid = m4.id)";


	public static String USER_SESSION_UID = "USER_SESSION_UID";
	
	public static String USER_COOKIES_NAME = "ybj-user-d";
	
	public static int USER_KIND = 0;//普通用户
	public static int USER_MERCHANT = 1;//商户
	
	@ApiModelProperty(value = "uid")
	@TableId(type = IdType.AUTO)
    private Integer uid;

    private Integer userType;

    private String mobile;
    
    private String pwd;

    private String weixin;

    private String weixinImg;
    
    private String logoImg;

    private String storeName;

    private String storeRemark;

    private String tradeStall;
    
    private String random;
    
    private String loginCookies;
    
    @ApiModelProperty(value = "最近更新商品时间")
   	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date productUpdateTime;

    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

    @ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;

    @ApiModelProperty(value = "上次登陆时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date lastLoginTime;

    //0 禁止登录   1允许登录
    private Integer loginStatus;
    //0 未审核    1已审核 
    private Integer auditStatus;
    
    private String auditRemark;
    
    
    private Integer marketLid;
    
    private Integer marketSid;
    
	private transient String sctime;
	
	private transient Integer count;

	private transient String marketCname;
	
	private transient String marketMname;
	
	private transient String marketLname;
	
	private transient String marketSname;
	

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getWeixinImg() {
        return weixinImg;
    }

    public void setWeixinImg(String weixinImg) {
        this.weixinImg = weixinImg == null ? null : weixinImg.trim();
    }

    public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreRemark() {
        return storeRemark;
    }

    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark == null ? null : storeRemark.trim();
    }

    public String getTradeStall() {
        return tradeStall;
    }

    public void setTradeStall(String tradeStall) {
        this.tradeStall = tradeStall == null ? null : tradeStall.trim();
    }

    
    
    public Date getProductUpdateTime() {
		return productUpdateTime;
	}

	public void setProductUpdateTime(Date productUpdateTime) {
		this.productUpdateTime = productUpdateTime;
	}

	public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMarketCname() {
		return marketCname;
	}

	public void setMarketCname(String marketCname) {
		this.marketCname = marketCname;
	}

	public String getMarketMname() {
		return marketMname;
	}

	public void setMarketMname(String marketMname) {
		this.marketMname = marketMname;
	}

	public String getMarketLname() {
		return marketLname;
	}

	public void setMarketLname(String marketLname) {
		this.marketLname = marketLname;
	}

	public String getMarketSname() {
		return marketSname;
	}

	public void setMarketSname(String marketSname) {
		this.marketSname = marketSname;
	}

	public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    

    public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Integer getMarketLid() {
		return marketLid;
	}

	public void setMarketLid(Integer marketLid) {
		this.marketLid = marketLid;
	}
	
	

	public Integer getMarketSid() {
		return marketSid;
	}

	public void setMarketSid(Integer marketSid) {
		this.marketSid = marketSid;
	}

	public String getSctime() {
		return sctime;
	}

	public void setSctime(String sctime) {
		this.sctime = sctime;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getLoginCookies() {
		return loginCookies;
	}

	public void setLoginCookies(String loginCookies) {
		this.loginCookies = loginCookies;
	}





	public static class Builder {
        private UserInfo obj;

        public Builder() {
            this.obj = new UserInfo();
        }

        public Builder uid(Integer uid) {
            obj.uid = uid;
            return this;
        }

        public Builder userType(Integer userType) {
            obj.userType = userType;
            return this;
        }

        public Builder mobile(String mobile) {
            obj.mobile = mobile;
            return this;
        }

        public Builder weixin(String weixin) {
            obj.weixin = weixin;
            return this;
        }

        public Builder weixinImg(String weixinImg) {
            obj.weixinImg = weixinImg;
            return this;
        }

        public Builder storeName(String storeName) {
            obj.storeName = storeName;
            return this;
        }

        public Builder storeRemark(String storeRemark) {
            obj.storeRemark = storeRemark;
            return this;
        }

        public Builder tradeStall(String tradeStall) {
            obj.tradeStall = tradeStall;
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

        public Builder lastLoginTime(Date lastLoginTime) {
            obj.lastLoginTime = lastLoginTime;
            return this;
        }

        public Builder auditStatus(Integer auditStatus) {
            obj.auditStatus = auditStatus;
            return this;
        }

        public UserInfo build() {
            return this.obj;
        }
    }
}