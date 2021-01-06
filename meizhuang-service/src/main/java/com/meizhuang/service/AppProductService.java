package com.meizhuang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meizhuang.entity.AppProduct;
import com.meizhuang.mapper.meizhuang.AppProductMapper;


@Service
public class AppProductService extends BaseService<AppProductMapper, AppProduct>{

	@Autowired
	private AppProductMapper appProductMapper;
	
	
	public AppProduct getAppProcuctByAppId(Integer appId){
		return appProductMapper.getAppProcuctByAppId(appId);
	}
	
}
