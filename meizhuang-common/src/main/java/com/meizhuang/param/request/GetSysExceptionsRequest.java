package com.meizhuang.param.request;

import java.io.Serializable;

public class GetSysExceptionsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ipAddress;
	private String resourceUrl;
	private String useSystem;
	private String sysContents;
	private String ctime;

	public GetSysExceptionsRequest() {

	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getUseSystem() {
		return useSystem;
	}

	public void setUseSystem(String useSystem) {
		this.useSystem = useSystem;
	}

	public String getSysContents() {
		return sysContents;
	}

	public void setSysContents(String sysContents) {
		this.sysContents = sysContents;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

}