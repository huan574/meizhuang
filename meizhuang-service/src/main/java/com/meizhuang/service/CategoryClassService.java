package com.meizhuang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meizhuang.entity.CategoryClass;
import com.meizhuang.mapper.meizhuang.CategoryClassMapper;

@Service
public class CategoryClassService extends BaseService<CategoryClassMapper,CategoryClass>{

	@Autowired
	private CategoryClassMapper mapper;
	
	
}
