package com.meizhuang.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

public class FrameTemplate implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	public static final String selectFiled = " f.*,f.state state1,t.name,t.example_img exampleImg ";
	
	public static final String fromFiled = " frame_template f inner join template t on f.template_id = t.id ";
	
	
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "框架",required=true)
	@NotNull
	private Integer frameId;
	
	@ApiModelProperty(value = "模板",required=true)
	@NotNull
	private Integer templateId;
	
	
	@ApiModelProperty(value = "顺序",required=true)
	@NotNull
	private Integer order;
	
	@ApiModelProperty(value = "状态  1.开启  2.关闭")
	private Integer state;
	
	@ApiModelProperty(value = "状态  1.开启  2.关闭")
	private transient Integer state1;
	
	@ApiModelProperty(value = "模板名称")
	private transient String name;
	
	@ApiModelProperty(value = "示例图")
	private transient String exampleImg;
	
	@ApiModelProperty(value = "框架图片集合")
	private transient List<TemplateImg> templateImgList;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getFrameId() {
		return frameId;
	}


	public void setFrameId(Integer frameId) {
		this.frameId = frameId;
	}


	public Integer getTemplateId() {
		return templateId;
	}


	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getExampleImg() {
		return exampleImg;
	}


	public void setExampleImg(String exampleImg) {
		this.exampleImg = exampleImg;
	}


	public Integer getState1() {
		return state1;
	}


	public void setState1(Integer state1) {
		this.state1 = state1;
	}


	public List<TemplateImg> getTemplateImgList() {
		return templateImgList;
	}


	public void setTemplateImgList(List<TemplateImg> templateImgList) {
		this.templateImgList = templateImgList;
	}
	
	
	
}
