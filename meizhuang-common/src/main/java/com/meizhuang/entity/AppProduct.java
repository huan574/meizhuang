package com.meizhuang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;

@ApiModel(description = "产品管理")
public class AppProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer appId;

	@ApiModelProperty(value = "产品名称")
	private String appName;

	@ApiModelProperty(value = "产品图标")
	private String appIcon;

	@ApiModelProperty(value = "产品描述")
	private String appDesc;

	@ApiModelProperty(value = "最新版本号")
	private String appVersion;
	
	@ApiModelProperty(value = "产品下载地址")
	private String appDownloadUrl;
	
	@ApiModelProperty(value = "产品下载二维码")
	private String appDownloadImg;

	@ApiModelProperty(value = "产品域名地址")
    private String appDomainUrl;
	
	@ApiModelProperty(value ="代理服务IP")
	private String agentServerIp;
	
	@ApiModelProperty(value = "服务器地址")
	private String serverUrl;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private Date ctime;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private Date mtime;
	
	@ApiModelProperty(value = "状态")
	private Integer status;
	

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName == null ? null : appName.trim();
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon == null ? null : appIcon.trim();
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc == null ? null : appDesc.trim();
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion == null ? null : appVersion.trim();
	}

	public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl == null ? null : appDownloadUrl.trim();
    }

    public String getAppDownloadImg() {
        return appDownloadImg;
    }

    public void setAppDownloadImg(String appDownloadImg) {
        this.appDownloadImg = appDownloadImg == null ? null : appDownloadImg.trim();
    }
    
    public String getAppDomainUrl() {
        return appDomainUrl;
    }

    public void setAppDomainUrl(String appDomainUrl) {
        this.appDomainUrl = appDomainUrl == null ? null : appDomainUrl.trim();
    }
    
	public String getAgentServerIp() {
		return agentServerIp;
	}

	public void setAgentServerIp(String agentServerIp) {
		this.agentServerIp = agentServerIp;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
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
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public static class Builder {
		private AppProduct obj;

		public Builder() {
			this.obj = new AppProduct();
		}

		public Builder appId(Integer appId) {
			obj.appId = appId;
			return this;
		}

		public Builder appName(String appName) {
			obj.appName = appName;
			return this;
		}

		public Builder appIcon(String appIcon) {
			obj.appIcon = appIcon;
			return this;
		}

		public Builder appDesc(String appDesc) {
			obj.appDesc = appDesc;
			return this;
		}

		public Builder appVersion(String appVersion) {
			obj.appVersion = appVersion;
			return this;
		}

		public Builder appDownloadUrl(String appDownloadUrl) {
            obj.appDownloadUrl = appDownloadUrl;
            return this;
        }
		
		public Builder appDownloadImg(String appDownloadImg) {
            obj.appDownloadImg = appDownloadImg;
            return this;
        }

        public Builder appDomainUrl(String appDomainUrl) {
            obj.appDomainUrl = appDomainUrl;
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

		public AppProduct build() {
			return this.obj;
		}
	}

	@Override
	public String toString() {
		return "AppProduct [appId=" + appId + ", appName=" + appName + ", appIcon=" + appIcon + ", appDesc=" + appDesc + ", appVersion=" + appVersion + ", appDownloadUrl=" + appDownloadUrl + ", appDownloadImg=" + appDownloadImg + ", appDomainUrl=" + appDomainUrl + ", ctime=" + ctime + ", mtime=" + mtime + "]";
	}

}