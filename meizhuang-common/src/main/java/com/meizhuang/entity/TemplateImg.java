package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class TemplateImg implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String selectFiled = " t.*,s.name sectionUiName ";
	
	public static final String fromFiled = " template_img t LEFT JOIN section_ui s on s.id = t.section_ui ";
	
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "关联表id",required=true)
	@NotNull
	private Integer frameTemplateId;
	
	@ApiModelProperty(value = "所在位置",required=true)
	@NotNull
	private Integer theLocation;
	
	@ApiModelProperty(value = "图片id",required=true)
	@NotEmpty
	private String imgUrl;
	
	@ApiModelProperty(value = "是否是跳转  0.是  1.不是",required=true)
	@NotNull
	private Integer isSection;
	
	@ApiModelProperty(value = "跳转类型")
	private Integer sectionType;
	
	@ApiModelProperty(value = "跳转地址")
	private String sectionUrl;
	
	@ApiModelProperty(value = "跳转UI")
	private String sectionUi;
	
	@ApiModelProperty(value = "跳转UI名称")
	private transient String sectionUiName;
	
	@ApiModelProperty(value = "弹窗内容")
	private String popupContent;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFrameTemplateId() {
		return frameTemplateId;
	}

	public void setFrameTemplateId(Integer frameTemplateId) {
		this.frameTemplateId = frameTemplateId;
	}

	

	public Integer getTheLocation() {
		return theLocation;
	}

	public void setTheLocation(Integer theLocation) {
		this.theLocation = theLocation;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getIsSection() {
		return isSection;
	}

	public void setIsSection(Integer isSection) {
		this.isSection = isSection;
	}

	public String getSectionUrl() {
		return sectionUrl;
	}

	public void setSectionUrl(String sectionUrl) {
		this.sectionUrl = sectionUrl;
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

	public Integer getSectionType() {
		return sectionType;
	}

	public void setSectionType(Integer sectionType) {
		this.sectionType = sectionType;
	}

	public String getSectionUi() {
		return sectionUi;
	}

	public void setSectionUi(String sectionUi) {
		this.sectionUi = sectionUi;
	}

	public String getSectionUiName() {
		return sectionUiName;
	}

	public void setSectionUiName(String sectionUiName) {
		this.sectionUiName = sectionUiName;
	}

	public String getPopupContent() {
		return popupContent;
	}

	public void setPopupContent(String popupContent) {
		this.popupContent = popupContent;
	}
	
	
	
}
