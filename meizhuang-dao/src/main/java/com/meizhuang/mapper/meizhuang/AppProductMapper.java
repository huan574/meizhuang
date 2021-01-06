package com.meizhuang.mapper.meizhuang;

import com.meizhuang.mapper.base.Mapper;

import org.apache.ibatis.annotations.Param;

import com.meizhuang.entity.AppProduct;




public interface AppProductMapper extends Mapper<AppProduct>{

	AppProduct getAppProcuctByAppId(@Param(value = "appId") Integer appId);
}
