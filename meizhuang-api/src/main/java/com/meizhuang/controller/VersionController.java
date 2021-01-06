package com.meizhuang.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meizhuang.aspect.TokenIgnore;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.AppProduct;
import com.meizhuang.entity.Version;
import com.meizhuang.param.request.VersionRequest;
import com.meizhuang.param.response.VersionResponse;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.AppProductService;
import com.meizhuang.service.VersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "版本管理接口")
@RestController
@RequestMapping(value = "/version")
public class VersionController extends BaseController {

	@Autowired
	private VersionService versionService;
	@Autowired
	private AppProductService appProductService;

	
	@TokenIgnore
	@ApiOperation(value = "获取服务器", notes = "获取服务器接口")
	@RequestMapping(value = "/agent_server", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonResult<VersionResponse> agent_server(@RequestParam(value = "appId", required = true) Integer appId) {
		AppProduct appProduct = appProductService.getAppProcuctByAppId(appId);
		if(appProduct == null){
			return JsonResult.buildError("产品不存在");
		}
		return null;
	}
	
	
	@ApiOperation(value = "根据域名获取产品编号", notes = "根据域名获取产品编号接口")
	@RequestMapping(value = "/get_all_2")
	public JsonResult<List<AppProduct>> get_all() {
		try{
			List<AppProduct> appProduct = appProductService.selectAll();
			if (appProduct == null) {
				return JsonResult.buildError("暂无产品相关信息");
			}
		
			return JsonResult.buildSuccess(appProduct);
		}catch(Exception e){
			
		}
		return JsonResult.buildSuccess();
		
	}
	
	@TokenIgnore
	@ApiOperation(value = "版本管理", notes = "版本管理接口")
	@RequestMapping(value = "/check", method = {RequestMethod.POST, RequestMethod.GET})
	public JsonResult<VersionResponse> check(@Validated VersionRequest versionRequest, BindingResult bindingResult) {
		
		Integer appId = versionRequest.getAppId();
		Version version = versionService.getVersionByChannel(versionRequest.getOsName(),
				appId);
		
		AppProduct appProduct = appProductService.getAppProcuctByAppId(appId);
		//ServerList serverList = serverListServer.getServerListByChannel(appId);
		//versionService.getVersionList();
		
		if (version == null) {
			return JsonResult.buildError("暂无版本更新信息");
		}
		
		if(appProduct == null){
			return JsonResult.buildError("服务器不存在");
		}
		
		Integer nowVersion = Integer.valueOf(version.getVersion().replace(".", ""));
		Integer oldVersion = Integer.valueOf(versionRequest.getVersion().replace(".", ""));
		VersionResponse response = new VersionResponse();
		BeanUtils.copyProperties(version, response);
		if (nowVersion > oldVersion) {
			response.setIsUpdate(1);
		} else {
			response.setIsUpdate(0);
		}
		response.setServerUrl(appProduct.getServerUrl());
		
		return JsonResult.buildSuccess(response);
	}

}