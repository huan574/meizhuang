package com.meizhuang.mapper.base;

public class BaseSelectProvider<T> {

	public String selectCount(BaseWrapper<T> sql) {
		return "SELECT COUNT(1) FROM " + sql.getTableFiled() + " WHERE " + sql.getWhereFiled();
	}

	public String select(BaseWrapper<T> sql) {
		return "SELECT " + sql.getSelectFiled() + " FROM " + sql.getTableFiled() + " WHERE " + sql.getWhereFiled();
	}

}
