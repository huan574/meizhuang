package com.meizhuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.aspect.SignIgnore;
import com.meizhuang.aspect.TokenIgnore;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.AppProduct;
import com.meizhuang.param.request.AppProductRequest;
import com.meizhuang.param.response.AppProductInfoResponse;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.AppProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "根据域名获取产品编号接口")
@RestController
@RequestMapping(value = "/app_product")
public class AppProductController extends BaseController {
	@Autowired
	private AppProductService appProductService;

	@SignIgnore
	@TokenIgnore
	@ApiOperation(value = "根据域名获取产品编号", notes = "根据域名获取产品编号接口")
	@RequestMapping(value = "/get_app_id", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult<AppProductInfoResponse> get_app_id(@Validated AppProductRequest appProductRequest, BindingResult bindingResult) {
		EntityWrapper<AppProduct> wrapper = new EntityWrapper<AppProduct>();
		wrapper.like("app_domain_url", appProductRequest.getAppDomainUrl());
		AppProduct appProduct = appProductService.selectOne(wrapper);
		if (appProduct == null) {
			return JsonResult.buildError("暂无产品相关信息");
		}
		AppProductInfoResponse response = new AppProductInfoResponse();
		response.setAppId(appProduct.getAppId());
		response.setAppIcon(appProduct.getAppIcon());
		response.setAppName(appProduct.getAppName());
		response.setInterfaceHost(appProduct.getAgentServerIp());
		return JsonResult.buildSuccess(response);
	}

}
