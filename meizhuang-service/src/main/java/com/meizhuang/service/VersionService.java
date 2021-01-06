package com.meizhuang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meizhuang.entity.Version;
import com.meizhuang.mapper.meizhuang.VersionMapper;

@Service
public class VersionService extends BaseService<VersionMapper, Version> {

	@Autowired
	private VersionMapper versionMapper;
	
	public Version getVersionByChannel(String osName, Integer appId){
		return versionMapper.getVersionByChannel(osName, appId);
	}
	
	
	public List<Version> getVersionList(){
		return versionMapper.getVersionList();
	}
}
