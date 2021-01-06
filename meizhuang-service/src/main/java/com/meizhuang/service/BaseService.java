package com.meizhuang.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.mapper.base.Mapper;


public class BaseService<M extends Mapper<T>, T> extends ServiceImpl<M, T> {

	
	public int selectCount(BaseWrapper<T> sql) {
		if (StringUtils.isEmpty(sql.getTableFiled())) {
			return 0;
		}
		return SqlHelper.retCount(baseMapper.selectCountBySql(sql));
	}

	
	public Page<T> selectPage(Page<T> page, BaseWrapper<T> sql) {
		if (StringUtils.isEmpty(sql.getSelectFiled()) || StringUtils.isEmpty(sql.getTableFiled())) {
			page.setRecords(null);
		} else {
			page.setRecords(baseMapper.selectPageBySql(page, sql));
		}
		return page;
	}

	
	public T selectOne(BaseWrapper<T> sql) {
		if (StringUtils.isEmpty(sql.getSelectFiled()) || StringUtils.isEmpty(sql.getTableFiled())) {
			return null;
		}
		return baseMapper.selectOneBySql(sql);
	}
	
	
	public List<T> selectByList(BaseWrapper<T> sql) {
		if (StringUtils.isEmpty(sql.getSelectFiled()) || StringUtils.isEmpty(sql.getTableFiled())) {
			return null;
		}
		return baseMapper.selectListBySql(sql);
	}
	

	
	public List<T> selectAll() {
		Wrapper<T> warpper = new EntityWrapper<T>();
		return baseMapper.selectList(warpper);
	}

}
