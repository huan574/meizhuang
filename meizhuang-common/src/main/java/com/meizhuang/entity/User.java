package com.meizhuang.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@ApiModel(description = "用户信息")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String selectFiled = "u.*, (select  nickname  from  user  where uid=u.agent_id) agentName," +
			" (select  CONCAT(uid,'(',if(LENGTH(trim(nickname))<1,'暂无昵称',nickname),')')  from  user  where to_invite_code=u.from_invite_code) inviteName," +
			" (select  user_type  from  user  where to_invite_code=u.from_invite_code) inviteType," +
			" app.app_name, a.balance,r.level_name userLevelName ";

	public static final String fromFiled = "user u inner join app_product app on u.app_id = app.app_id inner join account a on u.uid = a.uid left join user_level_requirement r on r.user_level = u.user_level";

	@ApiModelProperty(value = "账号")
	@TableId(type = IdType.AUTO)
	private Integer uid;

	@ApiModelProperty(value = "手机号对应的国家编码")
	private String countryCode;

	@ApiModelProperty(value = "手机号码")
	private String mobileNumber;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "登录密码")
	private String loginPword;

	@ApiModelProperty(value = "交易密码")
	private String capitalPword;

	@ApiModelProperty(value = "实名认证状态：0、未进行认证，1、实名认证")
	private Byte authStatus;

	@ApiModelProperty(value = "会员等级：1、初级会员，2、中级会员，3、高级会员")
	private Byte userLevel;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "头像")
	private byte[] headPhoto;

	@ApiModelProperty(value = "用户登录状态：1：正常，2禁用")
	private Byte loginStatus;

	@ApiModelProperty(value = "用户登录解冻时间")
	private Date loginexpireTime;

	@ApiModelProperty(value = "用户抢单状态：1：正常，2禁用")
	private Byte excStatus;

	@ApiModelProperty(value = "用户交易解冻时间")
	private Date excexpireTime;

	@ApiModelProperty(value = "用户提现状态：1：正常，2禁用")
	private Byte withdrawStatus;

	@ApiModelProperty(value = "用户提现解冻时间")
	private Date withdrawexpireTime;

	@ApiModelProperty(value = "锁定过期时间")
	private Date lockexpireTime;

	@ApiModelProperty(value = "登录禁用原因")
	private String loginRemark;

	@ApiModelProperty(value = "抢单禁用原因")
	private String excRemark;

	@ApiModelProperty(value = "提现禁用原因")
	private String withdrewRemark;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date ctime;

	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
	private Date mtime;

	@ApiModelProperty(value = "上次登陆时间")
	private Date lastLoginTime;

	@ApiModelProperty(value = "是否开启了谷歌认证:0-未开启,1-开启")
	private Byte googleAuthenticatorStatus;

	@ApiModelProperty(value = "谷歌认证key值")
	private String googleAuthenticatorKey;

	@ApiModelProperty(value = "是否开启了手机认证:0-未开启,1-开启")
	private Byte mobileAuthenticatorStatus;

	@ApiModelProperty(value = "用户类型，0：代理用户，1：刷单用户")
	private Byte userType;

	@ApiModelProperty(value = "工作状态:0-休息,1-工作")
	private Byte workStatus;

	@ApiModelProperty(value = "代理商编号")
	private Integer agentId;

	@ApiParam(hidden = true)
	private transient User agent; // 代理商信息

	@ApiModelProperty(value = "用户来源 1：闲蛋APP")
	private Integer appId;

	@ApiModelProperty(value = "被邀请码")
	private String fromInviteCode;

	@ApiModelProperty(value = "我的邀请码")
	private String toInviteCode;

	@ApiModelProperty(value = "佣金比例")
	private BigDecimal commisionScale;

	@ApiModelProperty(value = "违规次数")
	private Integer violationNumber;

	@ApiModelProperty(value = "注册来源")
	private transient String appName;

	@ApiModelProperty(value = "头像")
	private transient String headPt;

	@ApiModelProperty(value = "用户余额")
	private transient BigDecimal balance;

	@ApiModelProperty(value = "代理商名称")
	private transient String agentName;

	@ApiModelProperty(value = "关联支付商户号")
	private transient String mchId;

	@ApiModelProperty(value = "邀请人")
	private transient String inviteName;

	@ApiModelProperty(value = "邀请人类型")
	private transient Integer inviteType;
	
	@ApiModelProperty(value = "手机IMEI")
	private  String imei;
	
	@ApiModelProperty(value = "手机型号")
	private  String phoneSystemModel;
	
	@ApiModelProperty(value = "手机产商")
	private  String deviceBrand;
	
	@ApiModelProperty(value = "设备名称")
	private  String deviceName;
	
	@ApiModelProperty(value = "还款中总金额")
	private  transient  BigDecimal processedAmpunt;

	@ApiModelProperty(value = "会员等级名称")
	private transient String userLevelName;
	
	
	
	
	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public BigDecimal getProcessedAmpunt() {
		return processedAmpunt;
	}

	public void setProcessedAmpunt(BigDecimal processedAmpunt) {
		this.processedAmpunt = processedAmpunt;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneSystemModel() {
		return phoneSystemModel;
	}

	public void setPhoneSystemModel(String phoneSystemModel) {
		this.phoneSystemModel = phoneSystemModel;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode == null ? null : countryCode.trim();
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getLoginPword() {
		return loginPword;
	}

	public void setLoginPword(String loginPword) {
		this.loginPword = loginPword == null ? null : loginPword.trim();
	}

	public String getCapitalPword() {
		return capitalPword;
	}

	public void setCapitalPword(String capitalPword) {
		this.capitalPword = capitalPword == null ? null : capitalPword.trim();
	}

	public Byte getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Byte authStatus) {
		this.authStatus = authStatus;
	}

	public Byte getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Byte userLevel) {
		this.userLevel = userLevel;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public byte[] getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(byte[] headPhoto) {
		this.headPhoto = headPhoto;
	}

	public Byte getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Byte loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getLoginexpireTime() {
		return loginexpireTime;
	}

	public void setLoginexpireTime(Date loginexpireTime) {
		this.loginexpireTime = loginexpireTime;
	}

	public Byte getExcStatus() {
		return excStatus;
	}

	public void setExcStatus(Byte excStatus) {
		this.excStatus = excStatus;
	}

	public Date getExcexpireTime() {
		return excexpireTime;
	}

	public void setExcexpireTime(Date excexpireTime) {
		this.excexpireTime = excexpireTime;
	}

	public Byte getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(Byte withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public Date getWithdrawexpireTime() {
		return withdrawexpireTime;
	}

	public void setWithdrawexpireTime(Date withdrawexpireTime) {
		this.withdrawexpireTime = withdrawexpireTime;
	}

	public Date getLockexpireTime() {
		return lockexpireTime;
	}

	public void setLockexpireTime(Date lockexpireTime) {
		this.lockexpireTime = lockexpireTime;
	}

	public String getLoginRemark() {
		return loginRemark;
	}

	public void setLoginRemark(String loginRemark) {
		this.loginRemark = loginRemark == null ? null : loginRemark.trim();
	}

	public String getExcRemark() {
		return excRemark;
	}

	public void setExcRemark(String excRemark) {
		this.excRemark = excRemark == null ? null : excRemark.trim();
	}

	public String getWithdrewRemark() {
		return withdrewRemark;
	}

	public void setWithdrewRemark(String withdrewRemark) {
		this.withdrewRemark = withdrewRemark == null ? null : withdrewRemark.trim();
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Byte getGoogleAuthenticatorStatus() {
		return googleAuthenticatorStatus;
	}

	public void setGoogleAuthenticatorStatus(Byte googleAuthenticatorStatus) {
		this.googleAuthenticatorStatus = googleAuthenticatorStatus;
	}

	public String getGoogleAuthenticatorKey() {
		return googleAuthenticatorKey;
	}

	public void setGoogleAuthenticatorKey(String googleAuthenticatorKey) {
		this.googleAuthenticatorKey = googleAuthenticatorKey == null ? null : googleAuthenticatorKey.trim();
	}

	public Byte getMobileAuthenticatorStatus() {
		return mobileAuthenticatorStatus;
	}

	public void setMobileAuthenticatorStatus(Byte mobileAuthenticatorStatus) {
		this.mobileAuthenticatorStatus = mobileAuthenticatorStatus;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Byte getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Byte workStatus) {
		this.workStatus = workStatus;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public String getFromInviteCode() {
		return fromInviteCode;
	}

	public void setFromInviteCode(String fromInviteCode) {
		this.fromInviteCode = fromInviteCode == null ? null : fromInviteCode.trim();
	}

	public String getToInviteCode() {
		return toInviteCode;
	}

	public void setToInviteCode(String toInviteCode) {
		this.toInviteCode = toInviteCode == null ? null : toInviteCode.trim();
	}

	public BigDecimal getCommisionScale() {
		return commisionScale;
	}

	public void setCommisionScale(BigDecimal commisionScale) {
		this.commisionScale = commisionScale;
	}

	public Integer getViolationNumber() {
		return violationNumber;
	}

	public void setViolationNumber(Integer violationNumber) {
		this.violationNumber = violationNumber;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getHeadPt() {
		return headPt;
	}

	public void setHeadPt(String headPt) {
		this.headPt = headPt;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}

	public static class Builder {
		private User obj;

		public Builder() {
			this.obj = new User();
		}

		public Builder uid(Integer uid) {
			obj.uid = uid;
			return this;
		}

		public Builder countryCode(String countryCode) {
			obj.countryCode = countryCode;
			return this;
		}

		public Builder mobileNumber(String mobileNumber) {
			obj.mobileNumber = mobileNumber;
			return this;
		}

		public Builder email(String email) {
			obj.email = email;
			return this;
		}

		public Builder loginPword(String loginPword) {
			obj.loginPword = loginPword;
			return this;
		}

		public Builder capitalPword(String capitalPword) {
			obj.capitalPword = capitalPword;
			return this;
		}

		public Builder authStatus(Byte authStatus) {
			obj.authStatus = authStatus;
			return this;
		}

		public Builder userLevel(Byte userLevel) {
			obj.userLevel = userLevel;
			return this;
		}

		public Builder nickname(String nickname) {
			obj.nickname = nickname;
			return this;
		}

		public Builder headPhoto(byte[] headPhoto) {
			obj.headPhoto = headPhoto;
			return this;
		}

		public Builder loginStatus(Byte loginStatus) {
			obj.loginStatus = loginStatus;
			return this;
		}

		public Builder loginexpireTime(Date loginexpireTime) {
			obj.loginexpireTime = loginexpireTime;
			return this;
		}

		public Builder excStatus(Byte excStatus) {
			obj.excStatus = excStatus;
			return this;
		}

		public Builder excexpireTime(Date excexpireTime) {
			obj.excexpireTime = excexpireTime;
			return this;
		}

		public Builder withdrawStatus(Byte withdrawStatus) {
			obj.withdrawStatus = withdrawStatus;
			return this;
		}

		public Builder withdrawexpireTime(Date withdrawexpireTime) {
			obj.withdrawexpireTime = withdrawexpireTime;
			return this;
		}

		public Builder lockexpireTime(Date lockexpireTime) {
			obj.lockexpireTime = lockexpireTime;
			return this;
		}

		public Builder loginRemark(String loginRemark) {
			obj.loginRemark = loginRemark;
			return this;
		}

		public Builder excRemark(String excRemark) {
			obj.excRemark = excRemark;
			return this;
		}

		public Builder withdrewRemark(String withdrewRemark) {
			obj.withdrewRemark = withdrewRemark;
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

		public Builder googleAuthenticatorStatus(Byte googleAuthenticatorStatus) {
			obj.googleAuthenticatorStatus = googleAuthenticatorStatus;
			return this;
		}

		public Builder googleAuthenticatorKey(String googleAuthenticatorKey) {
			obj.googleAuthenticatorKey = googleAuthenticatorKey;
			return this;
		}

		public Builder mobileAuthenticatorStatus(Byte mobileAuthenticatorStatus) {
			obj.mobileAuthenticatorStatus = mobileAuthenticatorStatus;
			return this;
		}

		public Builder userType(Byte userType) {
			obj.userType = userType;
			return this;
		}

		public Builder workStatus(Byte workStatus) {
			obj.workStatus = workStatus;
			return this;
		}

		public Builder agentId(Integer agentId) {
			obj.agentId = agentId;
			return this;
		}

		public Builder appId(Integer appId) {
			obj.appId = appId;
			return this;
		}

		public Builder fromInviteCode(String fromInviteCode) {
			obj.fromInviteCode = fromInviteCode;
			return this;
		}

		public Builder toInviteCode(String toInviteCode) {
			obj.toInviteCode = toInviteCode;
			return this;
		}

		public Builder commisionScale(BigDecimal commisionScale) {
			obj.commisionScale = commisionScale;
			return this;
		}

		public Builder violationNumber(Integer violationNumber) {
			obj.violationNumber = violationNumber;
			return this;
		}

		public User build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", countryCode=" + countryCode + ", mobileNumber=" + mobileNumber + ", email=" + email + ", loginPword=" + loginPword + ", capitalPword=" + capitalPword + ", authStatus=" + authStatus + ", userLevel=" + userLevel + ", nickname=" + nickname + ", headPhoto=" + Arrays.toString(headPhoto) + ", loginStatus=" + loginStatus + ", loginexpireTime=" + loginexpireTime + ", excStatus=" + excStatus + ", excexpireTime=" + excexpireTime + ", withdrawStatus=" + withdrawStatus + ", withdrawexpireTime=" + withdrawexpireTime + ", lockexpireTime=" + lockexpireTime + ", loginRemark=" + loginRemark + ", excRemark=" + excRemark + ", withdrewRemark=" + withdrewRemark + ", ctime=" + ctime + ", mtime=" + mtime + ", lastLoginTime=" + lastLoginTime + ", googleAuthenticatorStatus=" + googleAuthenticatorStatus + ", googleAuthenticatorKey=" + googleAuthenticatorKey + ", mobileAuthenticatorStatus=" + mobileAuthenticatorStatus + ", userType=" + userType + ", workStatus=" + workStatus + ", agentId=" + agentId + ", agent=" + agent + ", appId=" + appId + ", fromInviteCode=" + fromInviteCode + ", toInviteCode=" + toInviteCode + ", commisionScale=" + commisionScale + ", violationNumber=" + violationNumber + ", appName=" + appName + ", headPt=" + headPt + ", balance=" + balance + ", agentName=" + agentName + ", mchId=" + mchId + ", inviteName=" + inviteName + ", inviteType=" + inviteType + ", getUid()=" + getUid() + ", getCountryCode()=" + getCountryCode() + ", getMobileNumber()=" + getMobileNumber() + ", getEmail()=" + getEmail() + ", getLoginPword()=" + getLoginPword() + ", getCapitalPword()=" + getCapitalPword() + ", getAuthStatus()=" + getAuthStatus() + ", getUserLevel()=" + getUserLevel() + ", getNickname()=" + getNickname() + ", getHeadPhoto()=" + Arrays.toString(getHeadPhoto()) + ", getLoginStatus()=" + getLoginStatus() + ", getLoginexpireTime()=" + getLoginexpireTime() + ", getExcStatus()=" + getExcStatus() + ", getExcexpireTime()=" + getExcexpireTime() + ", getWithdrawStatus()=" + getWithdrawStatus() + ", getWithdrawexpireTime()=" + getWithdrawexpireTime() + ", getLockexpireTime()=" + getLockexpireTime() + ", getLoginRemark()=" + getLoginRemark() + ", getExcRemark()=" + getExcRemark() + ", getWithdrewRemark()=" + getWithdrewRemark() + ", getCtime()=" + getCtime() + ", getMtime()=" + getMtime() + ", getLastLoginTime()=" + getLastLoginTime() + ", getGoogleAuthenticatorStatus()=" + getGoogleAuthenticatorStatus() + ", getGoogleAuthenticatorKey()=" + getGoogleAuthenticatorKey() + ", getMobileAuthenticatorStatus()=" + getMobileAuthenticatorStatus() + ", getUserType()=" + getUserType() + ", getWorkStatus()=" + getWorkStatus() + ", getAppId()=" + getAppId() + ", getAgentId()=" + getAgentId() + ", getAgent()=" + getAgent() + ", getFromInviteCode()=" + getFromInviteCode() + ", getToInviteCode()=" + getToInviteCode() + ", getCommisionScale()=" + getCommisionScale() + ", getViolationNumber()=" + getViolationNumber() + ", getAppName()=" + getAppName() + ", getHeadPt()=" + getHeadPt() + ", getBalance()=" + getBalance() + ", getAgentName()=" + getAgentName() + ", getMchId()=" + getMchId() + ", getInviteName()=" + getInviteName() + ", getInviteType()=" + getInviteType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	


}