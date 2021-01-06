package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class Frame implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	public static final String selectFiled = " f.*,p.app_name appName ";
	
	public static final String fromFiled = " frame f INNER JOIN app_product p on f.app_id = p.app_id ";
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "框架名称",required=true)
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "效果图",required=true)
	private String renderings;
	
	@ApiModelProperty(value = "状态  1.可用  2.禁用")
	private Integer state;
	
	@ApiModelProperty(value = "风格  1.通用  2.定制")
	@NotNull
	private Integer style;
	
	
	@ApiModelProperty(value = "app模块  1.首页  2.我的 3.消息 4.订单 5.活动")
	@NotNull
	private Integer appMode;
	
	@ApiModelProperty(value = "产品id")
	@NotNull
	private Integer appId;
	
	@ApiModelProperty(value = "产品id")
	private transient String appName;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;
	
	
	@ApiModelProperty(value = "框架集合信息")
	private transient List<FrameTemplate> frameTemplateList;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRenderings() {
		return renderings;
	}

	public void setRenderings(String renderings) {
		this.renderings = renderings;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getAppMode() {
		return appMode;
	}

	public void setAppMode(Integer appMode) {
		this.appMode = appMode;
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

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public List<FrameTemplate> getFrameTemplateList() {
		return frameTemplateList;
	}

	public void setFrameTemplateList(List<FrameTemplate> frameTemplateList) {
		this.frameTemplateList = frameTemplateList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	
}
