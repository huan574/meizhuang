package com.meizhuang.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.Template;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.param.request.TemplateRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.TemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "模板接口")
@RestController
@RequestMapping(value = "/flow/template")
public class TemplateController extends BaseController{
	
	@Autowired
	private TemplateService templateService;
	
	@ApiOperation(value = "页面跳转", notes = "模板名称页面跳转")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("system/templateList");
	}
	
	
	@ApiOperation(value = "查询列表", notes = "")
	@RequestMapping(value = "/getTemplateList", method = RequestMethod.POST)
	public PageResult<Template> getTemplateList(@Validated TemplateRequest requestBody,PageRequest pageRequest, BindingResult bindingResult) {
		Page<Template> page = new Page<Template>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		EntityWrapper<Template> wrapper = new EntityWrapper<Template>();
		
		if(!StringUtils.isEmpty(requestBody.getName())) {
			wrapper.eq("name", requestBody.getName());
		}
		
		if(requestBody.getState()!=null) {
			wrapper.eq("state", requestBody.getState());
		}
		
		// 查询数据返回总记录数和数据
		templateService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	@ApiOperation(value = "添加", notes = "")
	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
	public JsonResult<String> addTemplate(@Validated Template requestBody,
			BindingResult bindingResult) {
		boolean b = templateService.insert(requestBody);
		if (b) {
			return JsonResult.buildSuccess("添加成功");
		}
		return JsonResult.buildError("添加失败");
	}
	
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public JsonResult<String> updateTemplate(@Validated Template requestBody,
			BindingResult bindingResult) {
		Template info = templateService.selectById(requestBody.getId());
		if (info == null) {
			return JsonResult.buildError("该模板不存在");
		}
		Template entity = new Template();
		BeanUtils.copyProperties(requestBody, entity);
		boolean b = templateService.updateById(entity);
		if (b) {
			return JsonResult.buildSuccess("修改成功");
		}
		return JsonResult.buildError("修改失败");
	}
	
}
