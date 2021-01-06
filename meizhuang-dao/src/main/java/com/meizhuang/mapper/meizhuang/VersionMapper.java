package com.meizhuang.mapper.meizhuang;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.meizhuang.entity.Version;
import com.meizhuang.mapper.base.Mapper;

public interface VersionMapper extends Mapper<Version> {

	//@Select("select *from version where os_name = #{osName} and channel = #{channel}")
	Version getVersionByChannel(@Param(value = "osName") String osName, @Param(value="appId") Integer appId);
	
	@Select("select *from version")
	List<Version> getVersionList();
}
