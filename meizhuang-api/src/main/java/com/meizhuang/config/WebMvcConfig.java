package com.meizhuang.config;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.interceptor.SessionInterceptor;
import com.meizhuang.interceptor.SystemInterceptor;
import com.meizhuang.interceptor.TokenInterceptor;
import com.meizhuang.utils.SystemParameterUtils;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public static final String USE_SYSTEM = "meizhuang-api";

	private static Set<String> ignore = new HashSet<String>();

	static {
		ignore.add("/swagger-resources"); // API文档接口
	}

	@Autowired
	TokenInterceptor tokenInterceptor;
	@Autowired
	SystemInterceptor systemInterceptor;
	@Autowired
	SessionInterceptor sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(tokenInterceptor).addPathPatterns("/**") //
//				.excludePathPatterns(ignore.toArray(new String[ignore.size()]));
//
//		registry.addInterceptor(systemInterceptor).addPathPatterns("/**") //
//				.excludePathPatterns(ignore.toArray(new String[ignore.size()]));
		
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**") 
		.excludePathPatterns(ignore.toArray(new String[ignore.size()]));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/File/**").addResourceLocations("file:File/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        
        String path =  SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
        registry.addResourceHandler("/getFile/**").addResourceLocations("file:"+path+"/");
	}

}
