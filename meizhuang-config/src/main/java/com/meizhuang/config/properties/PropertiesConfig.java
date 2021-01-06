package com.meizhuang.config.properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

	@Resource
	private Environment env;

	@PostConstruct
	public void setProperties() {
		PropertiesUtils.setEnvironment(env);
	}

}
