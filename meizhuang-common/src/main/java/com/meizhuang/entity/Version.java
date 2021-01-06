package com.meizhuang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;

@ApiModel(description = "版本管理")
public class Version implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	public static final String selectFiled = " t.*,s.app_name appName ";
	
	public static final String fromFiled = " version t inner JOIN app_product s on s.app_id = t.app_id ";
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "产品ID")
	private Integer appId;
	
	@ApiModelProperty(value = "产品ID")
	private transient String appName;
	
	@ApiModelProperty(value = "版本号")
    private String version;

	@ApiModelProperty(value = "更新内容")
    private String contentEn;

	@ApiModelProperty(value = "下载地址")
    private String downloadUrl;
    
    @ApiModelProperty(value = "是否需要强制升级(0否、1是)")
    private Byte forceUpdate;

    @ApiModelProperty(value = "操作系统(android、ios)")
    private String osName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @TableField(update="now()")
    private Date mtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

	public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn == null ? null : contentEn.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public Byte getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Byte forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName == null ? null : osName.trim();
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

    
    
    
    
    public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}





	public static class Builder {
        private Version obj;

        public Builder() {
            this.obj = new Version();
        }

        public Builder id(Integer id) {
            obj.id = id;
            return this;
        }

        public Builder version(String version) {
            obj.version = version;
            return this;
        }

        public Builder contentEn(String contentEn) {
            obj.contentEn = contentEn;
            return this;
        }

        public Builder downloadUrl(String downloadUrl) {
            obj.downloadUrl = downloadUrl;
            return this;
        }

        public Builder forceUpdate(Byte forceUpdate) {
            obj.forceUpdate = forceUpdate;
            return this;
        }

        public Builder osName(String osName) {
            obj.osName = osName;
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

        public Version build() {
            return this.obj;
        }
    }

	@Override
	public String toString() {
		return "Version [id=" + id + ", version=" + version + ", contentEn=" + contentEn + ", downloadUrl=" + downloadUrl + ", forceUpdate=" + forceUpdate + ", osName=" + osName + ", ctime=" + ctime + ", mtime=" + mtime + "]";
	}

}