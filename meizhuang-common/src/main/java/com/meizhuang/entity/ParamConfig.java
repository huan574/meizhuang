package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

public class ParamConfig implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
    private Integer id;
	
	@ApiModelProperty(value = "参数名")
    private String name;
	
	@ApiModelProperty(value = "描述")
    private String fieldName;
	
	@ApiModelProperty(value = "参数值")
    private String value;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;
	
	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;
	
	@ApiModelProperty(value = "产品编号")
    private Integer appId;
	
	@ApiModelProperty(value = "代理商id")
    private Integer agentId;
	
	@ApiModelProperty(value = "详细说明")
    private String remake;
	
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
        this.name = name == null ? null : name.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
    
    
    

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}




	public static class Builder {
        private ParamConfig obj;

        public Builder() {
            this.obj = new ParamConfig();
        }

        public Builder id(Integer id) {
            obj.id = id;
            return this;
        }

        public Builder name(String name) {
            obj.name = name;
            return this;
        }

        public Builder fieldName(String fieldName) {
            obj.fieldName = fieldName;
            return this;
        }

        public Builder value(String value) {
            obj.value = value;
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

        public Builder appId(Integer appId) {
            obj.appId = appId;
            return this;
        }

        public Builder agentId(Integer agentId) {
            obj.agentId = agentId;
            return this;
        }
        
        public Builder remake(String remake) {
            obj.remake = remake;
            return this;
        }
        
        public ParamConfig build() {
            return this.obj;
        }
    }
}