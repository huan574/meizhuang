package com.meizhuang.mapper.base;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;

public class BaseWrapper<T> extends EntityWrapper<T> {

	private static final long serialVersionUID = 1L;

	private String selectFiled; // 要查询的字段
	private String tableFiled; // 查询的表
	private String whereFiled; // 查询条件

	public BaseWrapper(String selectFiled, String tableFiled) {
		this.selectFiled = selectFiled;
		this.tableFiled = tableFiled;
	}

	public BaseWrapper(String selectFiled, String tableFiled, String whereFiled) {
		this.selectFiled = selectFiled;
		this.tableFiled = tableFiled;
		this.whereFiled = whereFiled;
	}

	public String getSelectFiled() {
		return selectFiled;
	}

	public void setSelectFiled(String selectFiled) {
		this.selectFiled = selectFiled;
	}

	public BaseWrapper<T> setSelectFiled(String... columns) {
		StringBuilder builder = new StringBuilder();
		for (String column : columns) {
			if (StringUtils.isNotEmpty(column)) {
				if (builder.length() > 0) {
					builder.append(",");
				}
				builder.append(column);
			}
		}
		this.selectFiled = builder.toString();
		return this;
	}

	public String getTableFiled() {
		return tableFiled;
	}

	public void setTableFiled(String tableFiled) {
		this.tableFiled = tableFiled;
	}

	public String getWhereFiled() {
		if (StringUtils.isNotEmpty(whereFiled)) {
			return whereFiled;
		}
		return "1=1 " + getWhereSql();
	}

	public void setWhereFiled(String whereFiled) {
		this.whereFiled = whereFiled;
	}

	private String getWhereSql() {
		return replacePlaceholder(getSqlSegment());
	}

	private String replacePlaceholder(String sqlSegment) {
		if (StringUtils.isEmpty(sqlSegment)) {
			return StringUtils.EMPTY;
		}
		for (Map.Entry<String, Object> m : getParamNameValuePairs().entrySet()) {
			Object value = m.getValue();
			String paramValue;
			if (value != null && String.valueOf(value).indexOf("'") > -1) {
				paramValue = StringUtils.sqlParam(String.valueOf(value).replace("'", ""));
			} else {
				paramValue = StringUtils.sqlParam(value);
			}
			sqlSegment = sqlSegment.replaceAll("#\\{" + getParamAlias() + ".paramNameValuePairs." + m.getKey() + "\\}", paramValue);
		}
		return sqlSegment;
	}

}
