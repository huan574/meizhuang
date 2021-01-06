package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "留言")
public class LeaveWord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private String leaveWord;
	
	private String key;
	
	private Integer toUid;
	
	private Integer formUid;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaveWord() {
		return leaveWord;
	}

	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getToUid() {
		return toUid;
	}

	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	}

	public Integer getFormUid() {
		return formUid;
	}

	public void setFormUid(Integer formUid) {
		this.formUid = formUid;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
