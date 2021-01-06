package com.meizhuang.config.properties;

import org.springframework.core.env.Environment;

public class PropertiesUtils {

	private static Environment env = null;

	public static void setEnvironment(Environment env) {
		PropertiesUtils.env = env;
	}

	public static String getProperty(String key) {
		if (PropertiesUtils.env == null) {
			return null;
		}
		return PropertiesUtils.env.getProperty(key);
	}

}
