package com.meizhuang.param.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

@ApiModel(description = "分页查询请求参数")
public class PageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "当前页，默认 1", required = true, example = "1")
	private Integer page = 1;

	@ApiModelProperty(value = "每页显示多少条，默认 10", required = true, example = "10")
	private Integer limit = 10;

	@ApiModelProperty(value = "排序字段", required = true)
	private String sortField;

	@ApiModelProperty(value = "排序方式", required = true, allowableValues = "asc,desc")
	private String sortType;

	@ApiParam(hidden = true)
	private boolean ascState;

	public PageRequest() {

	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public boolean getAscState() {
		ascState = "asc".equals(sortType) ? true : false;
		return ascState;
	}

	public void setAscState(boolean ascState) {
		this.ascState = "asc".equals(sortType) ? true : false;
	}

}
