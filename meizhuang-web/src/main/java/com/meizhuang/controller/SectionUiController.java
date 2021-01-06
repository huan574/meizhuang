package com.meizhuang.controller;

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
import com.meizhuang.entity.SectionUi;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.SectionUiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "UI内部跳转管理")
@RestController
@RequestMapping(value = "/flow/sectionUI")
public class SectionUiController extends BaseController{
	
	@Autowired
	private SectionUiService sectionUiService;
	
	@ApiOperation(value = "页面跳转", notes = "模板名称页面跳转")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("system/sectionUIList");
	}
	
	
	@ApiOperation(value = "查询列表", notes = "")
	@RequestMapping(value = "/getSectionUiList", method = RequestMethod.POST)
	public PageResult<SectionUi> getSectionUiList(PageRequest pageRequest, BindingResult bindingResult) {
		Page<SectionUi> page = new Page<SectionUi>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		EntityWrapper<SectionUi> wrapper = new EntityWrapper<SectionUi>();

		
		// 查询数据返回总记录数和数据
		sectionUiService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	@ApiOperation(value = "添加", notes = "")
	@RequestMapping(value = "/addSectionUi", method = RequestMethod.POST)
	public JsonResult<String> addSectionUi(@Validated SectionUi requestBody,
			BindingResult bindingResult) {
		boolean b = sectionUiService.insert(requestBody);
		if (b) {
			return JsonResult.buildSuccess("添加成功");
		}
		return JsonResult.buildError("添加失败");
	}
	
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateSectionUi", method = RequestMethod.POST)
	public JsonResult<String> updateSectionUi(@Validated SectionUi requestBody,
			BindingResult bindingResult) {
		SectionUi info = sectionUiService.selectById(requestBody.getId());
		if (info == null) {
			return JsonResult.buildError("该跳转不存在");
		}
		SectionUi entity = new SectionUi();
		BeanUtils.copyProperties(requestBody, entity);
		boolean b = sectionUiService.updateById(entity);
		if (b) {
			return JsonResult.buildSuccess("修改成功");
		}
		return JsonResult.buildError("修改失败");
	}
}
