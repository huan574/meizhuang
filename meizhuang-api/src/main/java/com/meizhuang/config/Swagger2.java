package com.meizhuang.config;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Value("${swagger.show: false}")
	private boolean swaggerShow;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.enable(swaggerShow) //
				.apiInfo(apiInfo()) //
				.forCodeGeneration(true) //
				.select() //
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //
				.apis(RequestHandlerSelectors.any()) //
				.paths(PathSelectors.any()) //
				.build() //
				.securitySchemes(securitySchemes()) //
				.securityContexts(securityContexts());
	}

	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
		apiKeyList.add(new ApiKey("用户来源", "x-meizhuang-appId", "header"));
		apiKeyList.add(new ApiKey("令牌", "x-meizhuang-token", "header"));
		return apiKeyList;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<SecurityContext>();
		securityContexts.add( //
				SecurityContext.builder() //
						.securityReferences(defaultAuth())//
						.forPaths(PathSelectors.regex("^(?!auth).*$")) //
						.build() //
				);
		return securityContexts;
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<SecurityReference>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
				.title("API文档") //
				.description("此API提供接口调用") //
				.version("1.0") //
				.build();
	}

}
