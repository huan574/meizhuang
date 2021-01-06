package com.meizhuang.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;

//公告
public class Proclamation implements Serializable {
	
	@ApiModelProperty(value = "ID")
	@TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String text;

    private Integer state;

    private Integer orderNum;
    
    private Integer h5Id;
    //0 无效果  1跳转到H5页面
    private Integer clickResult;

    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date ctime;

    @ApiModelProperty(value = "更新时间")
   	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date mtime;

    private static final long serialVersionUID = 1L;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
    
    

    public Integer getH5Id() {
		return h5Id;
	}

	public void setH5Id(Integer h5Id) {
		this.h5Id = h5Id;
	}

	public Integer getClickResult() {
		return clickResult;
	}

	public void setClickResult(Integer clickResult) {
		this.clickResult = clickResult;
	}



	public static class Builder {
        private Proclamation obj;

        public Builder() {
            this.obj = new Proclamation();
        }

        public Builder id(Integer id) {
            obj.id = id;
            return this;
        }

        public Builder name(String name) {
            obj.name = name;
            return this;
        }

        public Builder text(String text) {
            obj.text = text;
            return this;
        }

        public Builder state(Integer state) {
            obj.state = state;
            return this;
        }

        public Builder orderNum(Integer orderNum) {
            obj.orderNum = orderNum;
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

        public Proclamation build() {
            return this.obj;
        }
    }
}