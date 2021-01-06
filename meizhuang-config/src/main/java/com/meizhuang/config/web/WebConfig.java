package com.meizhuang.config.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;

import com.meizhuang.config.xss.XssFilter;

@Configuration
public class WebConfig {

	/**
	 * xssFilter注册
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

}
