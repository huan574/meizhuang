package com.meizhuang.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.interceptor.AuthorityInterceptor;
import com.meizhuang.interceptor.SessionInterceptor;
import com.meizhuang.utils.SystemParameterUtils;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public static final String USE_SYSTEM = "meizhuang-web";

	@Autowired
	SessionInterceptor sessionInterceptor;
	@Autowired
	AuthorityInterceptor authorityInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
		//registry.addInterceptor(authorityInterceptor).addPathPatterns("/flow/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");


		String path =  SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
        registry.addResourceHandler("/getFile/**").addResourceLocations("file:"+path+"/");
        
        
	}

}
