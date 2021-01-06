package com.meizhuang.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.entity.AppProduct;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.AppProductService;
import com.meizhuang.utils.qrcode.QrCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "产品管理相关接口")
@RestController
@RequestMapping(value = "/flow/appProduct")
public class AppProductController {
	
	@Autowired
	AppProductService appProductService;

	@ApiOperation(value = "产品管理页面跳转", notes = "")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("system/appProduct");
	}

	@ApiOperation(value = "分页查询产品信息", notes = "")
	@RequestMapping(value = "/getAppProducts", method = RequestMethod.POST)
	public PageResult<AppProduct> getAppProducts(PageRequest pageRequest) {
		Page<AppProduct> page = new Page<AppProduct>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		appProductService.selectPage(page);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	@ApiOperation(value = "添加产品", notes = "")
	@RequestMapping(value = "/addAppProduct", method = RequestMethod.POST)
	public JsonResult<AppProduct> addAppProduct(AppProduct appProduct) {
		if (StringUtils.isBlank(appProduct.getAppName())) {
			return JsonResult.buildError("产品名称不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppIcon())) {
			return JsonResult.buildError("产品图标不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppDownloadUrl())) {
			return JsonResult.buildError("产品下载地址不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppDomainUrl())) {
			return JsonResult.buildError("产品域名地址不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAgentServerIp())) {
			return JsonResult.buildError("代理服务IP不能为空");
		}
		if (StringUtils.isBlank(appProduct.getServerUrl())) {
			return JsonResult.buildError("API服务器IP不能为空");
		}
		appProduct.setCtime(new Date());
		// 将产品下载地址转出二维码图片上传到云服务
		JsonResult<String> result = QrCodeUtils.encodeToYun(appProduct.getAppDownloadUrl());
		if (!result.isSuccess()) {
			return JsonResult.buildError(result);
		}
		String appDownLoadURL = result.getData();
		appProduct.setAppDownloadImg(appDownLoadURL);
		boolean isAdd = appProductService.insert(appProduct);
		if (!isAdd) {
			return JsonResult.buildError("添加失败");
		}
		return JsonResult.buildSuccess("添加成功");
	}

	@ApiOperation(value = "修改产品", notes = "")
	@RequestMapping(value = "/updateAppProduct", method = RequestMethod.POST)
	public JsonResult<AppProduct> updateAppProduct(AppProduct appProduct) {
		if (appProduct.getAppId() == null) {
			return JsonResult.buildError("产品ID不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppName())) {
			return JsonResult.buildError("产品名称不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppIcon())) {
			return JsonResult.buildError("产品图标不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppDownloadUrl())) {
			return JsonResult.buildError("产品下载地址不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAppDomainUrl())) {
			return JsonResult.buildError("产品域名地址不能为空");
		}
		if (StringUtils.isBlank(appProduct.getAgentServerIp())) {
			return JsonResult.buildError("代理服务IP不能为空");
		}
		if (StringUtils.isBlank(appProduct.getServerUrl())) {
			return JsonResult.buildError("API服务器IP不能为空");
		}
		if (appProduct.getStatus()==null) {
			return JsonResult.buildError("状态不能为空");
		}
		appProduct.setMtime(new Date());
		// 将产品下载地址转出二维码图片上传到云服务
		JsonResult<String> result = QrCodeUtils.encodeToYun(appProduct.getAppDownloadUrl());
		if (!result.isSuccess()) {
			return JsonResult.buildError(result);
		}
		String appDownLoadURL = result.getData();
		appProduct.setAppDownloadImg(appDownLoadURL);
		boolean isUpdate = appProductService.updateById(appProduct);
		if (!isUpdate) {
			return JsonResult.buildError("修改失败");
		}
		return JsonResult.buildSuccess("修改成功");
	}
}
