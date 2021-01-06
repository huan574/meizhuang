package com.meizhuang.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meizhuang.entity.ParamConfig;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.mapper.meizhuang.ParamConfigMapper;

@Repository
public class SystemParameterUtils {

	private static SystemParameterUtils systemParameterUtils;

	@Autowired
	private ParamConfigMapper paramConfigMapper;


	@PostConstruct
	public void init() {
		systemParameterUtils = this;
		systemParameterUtils.paramConfigMapper = this.paramConfigMapper;
	}
	
	/**
	 * 获取平台系统参数
	 * @param key
	 * @return
	 */
	public static  String get(SystemParameterEnum systemParameterEnum) {
		ParamConfig config = new ParamConfig.Builder().name(systemParameterEnum.getName()).appId(0).agentId(0).build();
		ParamConfig paramConfig = systemParameterUtils.paramConfigMapper.selectOne(config);
		if (paramConfig == null) {
			return "";
		}
		return paramConfig.getValue();
	}
	
	/**
	 * 获取平台系统参数
	 * @param key
	 * @return
	 */
	public static ParamConfig getSystemParamConfig(SystemParameterEnum systemParameterEnum) {
		ParamConfig config = new ParamConfig.Builder().name(systemParameterEnum.getName()).appId(0).agentId(0).build();
		ParamConfig paramConfig = systemParameterUtils.paramConfigMapper.selectOne(config);
		if (paramConfig == null) {
			return null;
		}
		return paramConfig;
	}

	

	/**
	 * 设置平台系统参数
	 * @param key 键
	 * @param value 值 
	 * @param desc 描述
	 * @return
	 */
	public static boolean set(SystemParameterEnum systemParameterEnum, String value, String desc) {
		ParamConfig config = new ParamConfig.Builder().agentId(0).appId(0).name(systemParameterEnum.getName()).fieldName(desc).value(value).build();
		int count = systemParameterUtils.paramConfigMapper.insert(config);
		if (count > 0) {
			return true;
		}
		return false;
	}


	

}
