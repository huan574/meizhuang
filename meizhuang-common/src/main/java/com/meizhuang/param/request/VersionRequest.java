package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "版本管理请求参数")
public class VersionRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "版本号")
	@NotBlank(message = "版本号不能为空")
    private String version;
	
	@ApiModelProperty(value = "强制升级(0否、1是)")
    private Byte forceUpdate;
	
    @ApiModelProperty(value = "操作系统类型(android、ios)")
    @NotBlank(message = "操作系统不能为空")
    private String osName;
    
    @ApiModelProperty(value = "appId")
    private Integer appId;
    
    @ApiModelProperty(value = "创建时间")
    private String ctime;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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
    
	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "VersionRequest [version=" + version + ", osName=" + osName + "]";
	}

}