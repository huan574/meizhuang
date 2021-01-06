package com.meizhuang.controller;

import java.util.List;

import org.apache.ibatis.javassist.tools.framedump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.Frame;
import com.meizhuang.entity.FrameTemplate;
import com.meizhuang.entity.TemplateImg;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.AppProductRequest;
import com.meizhuang.param.request.FrameFindRequest;
import com.meizhuang.param.response.AppProductInfoResponse;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.FrameService;
import com.meizhuang.service.FrameTemplateService;
import com.meizhuang.service.TemplateImgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "根据产品id获取模板")
@RestController
@RequestMapping(value = "/app_frame")
public class FrameController extends BaseController{

	@Autowired
	private FrameService frameService;
	
	@Autowired
	private FrameTemplateService frameTemplateService;
	
	@Autowired
	private TemplateImgService templateImgService;
	
	@ApiOperation(value = "根据产品id和模块获取框架模板", notes = "根据产品id和模块获取框架模")
	@RequestMapping(value = "/getFrameList", method = RequestMethod.POST)
	public JsonResult<Frame> getFrameList(@Validated FrameFindRequest requestBody, BindingResult bindingResult) {
		
		EntityWrapper<Frame> frameWrapper = new EntityWrapper<Frame>();
		frameWrapper.eq("state", 1);
		
		
		if(requestBody.getAppId()!=null) {
			frameWrapper.eq("app_id", requestBody.getAppId());
		}
		if(requestBody.getAppMode()!=null) {
			frameWrapper.eq("app_mode", requestBody.getAppMode());
		}
		
		if(requestBody.getId()!=null) {
			Frame frame1 = frameService.selectById(requestBody.getId());
			if(frame1.getState()!=1) {
				return JsonResult.buildError("该框架已禁用");
			}
			if(frame1.getAppMode()!=5) {
				return JsonResult.buildError("该框架不是活动页面");
			}
			
			frameWrapper.eq("id", requestBody.getId());
		}
		
		
		
		Frame frame = frameService.selectOne(frameWrapper);
		
		if(frame!=null) {
			
			BaseWrapper<FrameTemplate> frameTemplateWrapper = new BaseWrapper<FrameTemplate>(FrameTemplate.selectFiled,FrameTemplate.fromFiled);
			frameTemplateWrapper.eq("f.state", 1).eq("f.frame_id", frame.getId());
			frameTemplateWrapper.orderBy("f.order", true);
			List<FrameTemplate> frameTemplateList = frameTemplateService.selectByList(frameTemplateWrapper);
			
			if(frameTemplateList.size()>0) {
				
				for(int i=0;i<frameTemplateList.size();i++) {
					
					EntityWrapper<TemplateImg> templateImgWrapper = new EntityWrapper<TemplateImg>();
					templateImgWrapper.eq("frame_template_id", frameTemplateList.get(i).getId());
					List<TemplateImg> TemplateImgList =  templateImgService.selectList(templateImgWrapper);
					
					if(TemplateImgList.size()>0) {
						frameTemplateList.get(i).setTemplateImgList(TemplateImgList);
					}
	
				}
				
				frame.setFrameTemplateList(frameTemplateList);
			}
			
			return JsonResult.buildSuccess(frame);
		}
		
		
		
		return JsonResult.buildSuccess();
		
	}
}
