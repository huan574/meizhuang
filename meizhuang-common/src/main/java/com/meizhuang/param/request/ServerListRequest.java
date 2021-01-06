package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "serverList请求参数")
public class ServerListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "渠道(xd,xmy,avg.....)")
	@NotBlank(message = "渠道不能为空")
    private String channel;

    

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}