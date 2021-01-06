package com.meizhuang.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.authority.app.constant.AuthorityConstant;

@Configuration
public class AuthorityConfig {

	@Value("${spring.profiles.active}")
	private String profiles;

	@PostConstruct
	public void reload() {
		AuthorityConstant.reload("authority-" + profiles + ".properties");
	}

}
