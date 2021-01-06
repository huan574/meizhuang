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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.ParamConfig;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.param.request.ParamConfigRequest;
import com.meizhuang.param.request.ParamConfigWebRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.ParamConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "参数配置管理相关接口")
@RestController
@RequestMapping(value = "/flow/paramConfig")
public class ParamConfigController extends BaseController {

	@Autowired
	private ParamConfigService paramConfigService;

	@ApiOperation(value = "参数配置页面跳转", notes = "")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("system/paramConfigList");
	}

	@ApiOperation(value = "分页查询参数配置", notes = "")
	@RequestMapping(value = "/getParamConfig", method = RequestMethod.POST)
	public PageResult<ParamConfig> getParamConfig(PageRequest pageRequest, @Validated ParamConfigRequest requestBody) {
		Page<ParamConfig> page = new Page<ParamConfig>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		EntityWrapper<ParamConfig> wrapper = buildWrapper(requestBody);
		// 查询数据返回总记录数和数据
		paramConfigService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	private EntityWrapper<ParamConfig> buildWrapper(ParamConfigRequest requestBody) {
		EntityWrapper<ParamConfig> entityWrapper = new EntityWrapper<ParamConfig>();
		if (StringUtils.isNotEmpty(requestBody.getId())) {
			entityWrapper.eq("id", requestBody.getId());
		}
		if (StringUtils.isNotEmpty(requestBody.getName())) {
			entityWrapper.like("name", requestBody.getName());
		}
		if (StringUtils.isNotEmpty(requestBody.getFieldName())) {
			entityWrapper.like("field_name", requestBody.getFieldName());
		}
		if (requestBody.getAppId() != null) {
			entityWrapper.eq("app_id", requestBody.getAppId());
		}
		if (requestBody.getAgentId() != null) {
			entityWrapper.eq("agent_id", requestBody.getAgentId());
		}
		if (StringUtils.isNotEmpty(requestBody.getCtime())) {
			String[] ctime = requestBody.getCtime().split("~");
			if (ctime.length == 2) {
				entityWrapper.ge("ctime", ctime[0]);
				entityWrapper.le("ctime", ctime[1]);
			}
		}
		return entityWrapper;
	}

	@ApiOperation(value = "添加参数配置", notes = "根据ParamConfigWebRequest对象添加参数配置")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult<String> addParamConfig(@Validated ParamConfigWebRequest requestBody, BindingResult bindingResult) {
		EntityWrapper<ParamConfig> wrapper = new EntityWrapper<ParamConfig>(new ParamConfig.Builder().name(requestBody.getName()).fieldName(requestBody.getFieldName()).appId(requestBody.getAppId()).agentId(requestBody.getAgentId()).build());
		int count = paramConfigService.selectCount(wrapper);
		if (count > 0) {
			return JsonResult.buildError("该参数配置已存在");
		}
		ParamConfig pc = new ParamConfig();
		BeanUtils.copyProperties(requestBody, pc);
		boolean res = paramConfigService.insert(pc);
		if (res) {
			return JsonResult.buildSuccess("添加参数配置成功");
		}
		return JsonResult.buildError("添加参数配置失败");
	}

	@ApiOperation(value = "更新参数配置", notes = "根据ParamConfig对象更新参数配置")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult<String> updateParamConfig(@Validated ParamConfig requestBody, BindingResult bindingResult) {
		boolean res = paramConfigService.updateById(requestBody);
		if (res) {
			return JsonResult.buildSuccess("更新参数配置成功");
		}
		return JsonResult.buildError("更新参数配置失败");
	}

}
