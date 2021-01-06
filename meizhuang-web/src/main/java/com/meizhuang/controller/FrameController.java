package com.meizhuang.controller;

import java.util.List;

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
import com.meizhuang.entity.AppProduct;
import com.meizhuang.entity.Frame;
import com.meizhuang.entity.FrameTemplate;
import com.meizhuang.entity.SectionUi;
import com.meizhuang.entity.Template;
import com.meizhuang.entity.TemplateImg;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.FrameRequest;
import com.meizhuang.param.request.IdRequest;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.AppProductService;
import com.meizhuang.service.FrameService;
import com.meizhuang.service.FrameTemplateService;
import com.meizhuang.service.SectionUiService;
import com.meizhuang.service.TemplateImgService;
import com.meizhuang.service.TemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "框架接口")
@RestController
@RequestMapping(value = "/flow/frame")
public class FrameController {
	
	@Autowired
	private FrameService frameService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private FrameTemplateService frameTemplateService;
	
	@Autowired
	private TemplateImgService templateImgService;
	
	@Autowired
	private AppProductService appProductService;
	
	@Autowired
	private SectionUiService sectionUiService;
	
	
	@ApiOperation(value = "页面跳转", notes = "框架页面跳转")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("system/frameList");
	}
	
	
	@ApiOperation(value = "产品来源", notes = "")
	@RequestMapping(value = "/getAllAppProduct", method = RequestMethod.POST)
	public JsonResult<List<AppProduct>> getAppProducts() {
		List<AppProduct> products = appProductService.selectList(null);
		return JsonResult.buildSuccess(products);
	}
	
	@ApiOperation(value = "跳转UI", notes = "")
	@RequestMapping(value = "/getAllSectionUI", method = RequestMethod.POST)
	public JsonResult<List<SectionUi>> getAllSectionUI() {
		List<SectionUi> sectionUis = sectionUiService.selectList(null);
		return JsonResult.buildSuccess(sectionUis);
	}
	
	
	@ApiOperation(value = "查询框架", notes = "框架查询")
	@RequestMapping(value = "/getFrameList", method = RequestMethod.POST)
	public PageResult<Frame> getFrameList(@Validated FrameRequest requestBody,PageRequest pageRequest, BindingResult bindingResult) {
		Page<Frame> page = new Page<Frame>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<Frame> wrapper = new BaseWrapper<Frame>(Frame.selectFiled,Frame.fromFiled);
		
		if(!StringUtils.isEmpty(requestBody.getName())) {
			wrapper.eq("f.name", requestBody.getName());
		}
		if(requestBody.getStyle()!=null) {
			wrapper.eq("f.style", requestBody.getStyle());
		}
		if(requestBody.getAppMode()!=null) {
			wrapper.eq("f.app_mode", requestBody.getAppMode());
		}
		
		if(requestBody.getState()!=null) {
			wrapper.eq("f.state", requestBody.getState());
		}
		if(requestBody.getAppId()!=null) {
			wrapper.eq("f.app_id", requestBody.getAppId());
		}
		
		// 查询数据返回总记录数和数据
		frameService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	@ApiOperation(value = "添加框架", notes = "")
	@RequestMapping(value = "/addFrame", method = RequestMethod.POST)
	public JsonResult<String> addFrame(@Validated Frame requestBody,
			BindingResult bindingResult) {
		if(requestBody.getAppMode()!=5 && requestBody.getState()==1) {
			EntityWrapper<Frame> wrapper = new EntityWrapper<Frame>();
			wrapper.eq("state", 1).eq("app_mode", requestBody.getAppMode()).eq("app_id", requestBody.getAppId());
			Frame entity = new Frame();
			entity.setState(2);
			frameService.update(entity, wrapper);
		}
		boolean b = frameService.insert(requestBody);
		if (b) {
			return JsonResult.buildSuccess("添加成功");
		}
		return JsonResult.buildError("添加失败");
	}
	
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateFrame", method = RequestMethod.POST)
	public JsonResult<String> updateTemplate(@Validated Frame requestBody,
			BindingResult bindingResult) {
		
		if(requestBody.getAppMode()!=5 && requestBody.getState()==1) {
			EntityWrapper<Frame> wrapper = new EntityWrapper<Frame>();
			wrapper.eq("state", 1).eq("app_mode", requestBody.getAppMode()).eq("app_id", requestBody.getAppId());
			wrapper.notIn("id", requestBody.getId());
			Frame frame = frameService.selectOne(wrapper);
			if(frame!=null) {
				return JsonResult.buildError("不能同时打开相同的APP模板");
			}
		}else {
			Frame info = frameService.selectById(requestBody.getId());
			if (info == null) {
				return JsonResult.buildError("该模板不存在");
			}
		}
		
		
		Frame entity = new Frame();
		BeanUtils.copyProperties(requestBody, entity);
		boolean b = frameService.updateById(entity);
		if (b) {
			return JsonResult.buildSuccess("修改成功");
		}
		return JsonResult.buildError("修改失败");
	}
	
	@ApiOperation(value = "查询列表", notes = "")
	@RequestMapping(value = "/getTemplateList", method = RequestMethod.POST)
	public PageResult<FrameTemplate> getTemplateList(@Validated IdRequest requestBody,PageRequest pageRequest, BindingResult bindingResult) {
		Page<FrameTemplate> page = new Page<FrameTemplate>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<FrameTemplate> wrapper = new BaseWrapper<FrameTemplate>(FrameTemplate.selectFiled,FrameTemplate.fromFiled);
		
		if(requestBody.getId()!=null) {
			wrapper.eq("f.frame_id", requestBody.getId());
		}
		// 查询数据返回总记录数和数据
		frameTemplateService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	@ApiOperation(value = "获取模板", notes = "")
	@RequestMapping(value = "/getTemplate", method = RequestMethod.POST)
	public JsonResult<List<Template>> getPassTypeNameInfo() {
		
		EntityWrapper<Template> wrapper = new EntityWrapper<Template>();
		wrapper.eq("state", 1);
		List<Template> list = templateService.selectList(wrapper);
		
		if(list.size()==0) {
			return JsonResult.buildError("未获取到模板");
		}
		
		return JsonResult.buildSuccess(list);
	}
	
	
	
	@ApiOperation(value = "添加模板", notes = "")
	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
	public JsonResult<String> addTemplate(@Validated FrameTemplate requestBody,
			BindingResult bindingResult) {
		EntityWrapper<FrameTemplate> wrapper = new EntityWrapper<FrameTemplate>();
		wrapper.eq("frame_id", requestBody.getFrameId()).eq("`order`", requestBody.getOrder());
		FrameTemplate frameTemplate = frameTemplateService.selectOne(wrapper);
		if(frameTemplate!=null) {
			return JsonResult.buildError("不能添加重复顺序的模板");
		}
		
		boolean b = frameTemplateService.insert(requestBody);
		if (b) {
			return JsonResult.buildSuccess("添加成功");
		}
		return JsonResult.buildError("添加失败");
	}
	
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public JsonResult<String> updateTemplate(@Validated FrameTemplate requestBody,
			BindingResult bindingResult) {
		EntityWrapper<FrameTemplate> wrapper = new EntityWrapper<FrameTemplate>();
		wrapper.eq("frame_id", requestBody.getFrameId()).eq("`order`", requestBody.getOrder());
		wrapper.notIn("id", requestBody.getId());
		FrameTemplate frameTemplate = frameTemplateService.selectOne(wrapper);
		if(frameTemplate!=null) {
			return JsonResult.buildError("不能添加重复顺序的模板");
		}
		FrameTemplate entity = new FrameTemplate();
		BeanUtils.copyProperties(requestBody, entity);
		boolean b = frameTemplateService.updateById(entity);
		if (b) {
			return JsonResult.buildSuccess("修改成功");
		}
		return JsonResult.buildError("修改失败");
	}
	
	
	@ApiOperation(value = "查询图片列表", notes = "")
	@RequestMapping(value = "/getTemplateImgList", method = RequestMethod.POST)
	public PageResult<TemplateImg> getTemplateImgList(@Validated IdRequest requestBody,PageRequest pageRequest, BindingResult bindingResult) {
		Page<TemplateImg> page = new Page<TemplateImg>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<TemplateImg> wrapper = new BaseWrapper<TemplateImg>(TemplateImg.selectFiled,TemplateImg.fromFiled);
		
		if(requestBody.getId()!=null) {
			wrapper.eq("t.frame_template_id", requestBody.getId());
		}
		// 查询数据返回总记录数和数据
		templateImgService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	@ApiOperation(value = "添加模板", notes = "")
	@RequestMapping(value = "/addTemplateImg", method = RequestMethod.POST)
	public JsonResult<String> addTemplateImg(@Validated TemplateImg requestBody,
			BindingResult bindingResult) {
		
		if(requestBody.getSectionType()!=1004) {
			requestBody.setSectionUi(null);
		}
		
		boolean b = templateImgService.insert(requestBody);
		if (b) {
			return JsonResult.buildSuccess("添加成功");
		}
		return JsonResult.buildError("添加失败");
	}
	
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateTemplateImg", method = RequestMethod.POST)
	public JsonResult<String> updateTemplateImg(@Validated TemplateImg requestBody,
			BindingResult bindingResult) {
		EntityWrapper<TemplateImg> wrapper = new EntityWrapper<TemplateImg>();
		wrapper.eq("frame_template_id", requestBody.getFrameTemplateId()).eq("id", requestBody.getId());
		TemplateImg templateImg = templateImgService.selectOne(wrapper);
		if(templateImg==null) {
			return JsonResult.buildError("未找到该图片信息");
		}
		if(requestBody.getSectionType()!=1004) {
			requestBody.setSectionUi(null);
		}
		TemplateImg entity = new TemplateImg();
		BeanUtils.copyProperties(requestBody, entity);
		boolean b = templateImgService.updateById(entity);
		if (b) {
			return JsonResult.buildSuccess("修改成功");
		}
		return JsonResult.buildError("修改失败");
	}
	
	
	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/deleteTemplateImg", method = RequestMethod.POST)
	public JsonResult<String> deleteTemplateImg(@Validated IdRequest requestBody,
			BindingResult bindingResult) {
		EntityWrapper<TemplateImg> wrapper = new EntityWrapper<TemplateImg>();
		wrapper.eq("id", requestBody.getId());
		TemplateImg templateImg = templateImgService.selectOne(wrapper);
		if(templateImg==null) {
			return JsonResult.buildError("未找到该图片，请联系管理员");
		}
		boolean b = templateImgService.deleteById(requestBody.getId());
		if (b) {
			return JsonResult.buildSuccess("删除成功");
		}
		return JsonResult.buildError("删除失败");
	}
}
