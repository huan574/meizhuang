package com.meizhuang.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.Version;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.param.request.VersionAddRequest;
import com.meizhuang.param.request.VersionRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.AppProductService;
import com.meizhuang.service.VersionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/flow/version")
public class VersionController  extends BaseController{
	
	@Autowired
	private VersionService versionService;
	
	@Autowired
	private AppProductService appProductService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("system/version");
	}

	@RequestMapping(value = "/getVersionList", method = RequestMethod.POST)
	public PageResult<Version> getVersionList(PageRequest pageRequest, VersionRequest requestBody) {
		Page<Version> page = new Page<Version>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<Version> wrapper = buildWrapper(requestBody);
		// 查询数据返回总记录数和数据
		versionService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	private BaseWrapper<Version> buildWrapper(VersionRequest requestBody) {
		BaseWrapper<Version> entityWrapper = new BaseWrapper<Version>(Version.selectFiled,Version.fromFiled);
		if (StringUtils.isNotEmpty(requestBody.getVersion())) {
			entityWrapper.eq("t.version", requestBody.getVersion());
		}
		if (requestBody.getForceUpdate() != null) {
			entityWrapper.eq("t.force_update", requestBody.getForceUpdate().byteValue());
		}
		if (StringUtils.isNotEmpty(requestBody.getOsName())) {
			entityWrapper.eq("t.os_name", requestBody.getOsName());
		}
		if (StringUtils.isNotEmpty(requestBody.getCtime())) {
			String[] ctime = requestBody.getCtime().split("~");
			if (ctime.length == 2) {
				entityWrapper.ge("t.ctime", ctime[0]);
				entityWrapper.le("t.ctime", ctime[1]);
			}
		}
		return entityWrapper;
	}

	@ApiOperation(value = "添加版本", notes = "根据VersionAddRequest对象添加参数配置")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult<String> addVersion(@Validated VersionAddRequest requestBody, BindingResult bindingResult) {
		EntityWrapper<Version> wrapper = new EntityWrapper<Version>(new Version.Builder().version(requestBody.getVersion()).osName(requestBody.getOsName()).build());
		int count = versionService.selectCount(wrapper);
		if (count > 0) {
			return JsonResult.buildError("该版本已存在");
		}
		Version v = new Version();
		BeanUtils.copyProperties(requestBody, v);
		boolean res = versionService.insert(v);
		if (res) {
			return JsonResult.buildSuccess("添加版本成功");
		}
		return JsonResult.buildError("添加版本失败");
	}

	@ApiOperation(value = "修改版本", notes = "根据Version对象更新参数配置")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult<String> updateParamConfig(@Validated Version requestBody, BindingResult bindingResult) {
		boolean res = versionService.updateById(requestBody);
		if (res) {
			return JsonResult.buildSuccess("修改版本成功");
		}
		return JsonResult.buildError("修改版本失败");
	}
	
	
	
}
