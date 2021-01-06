package com.meizhuang.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface Mapper<T> extends BaseMapper<T> {

	@SelectProvider(type = BaseSelectProvider.class, method = "selectCount")
	Integer selectCountBySql(BaseWrapper<T> sql);

	@SelectProvider(type = BaseSelectProvider.class, method = "select")
	List<T> selectPageBySql(Pagination page, BaseWrapper<T> sql);

	@SelectProvider(type = BaseSelectProvider.class, method = "select")
	T selectOneBySql(BaseWrapper<T> sql);
	
	@SelectProvider(type = BaseSelectProvider.class, method = "select")
	List<T> selectListBySql(BaseWrapper<T> sql);

}
