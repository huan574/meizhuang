package com.meizhuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.entity.HtmlArticle;
import com.meizhuang.entity.Proclamation;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.HtmlArticleService;
import com.meizhuang.service.ProclamationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "公告接口")
@RestController
@RequestMapping(value = "/flow/proclamation")
public class ProclamationController {

	@Autowired
	private ProclamationService proclamationService;
	
	@Autowired
	private HtmlArticleService htmlArticleService;
	
	@ApiOperation(value = "页面跳转", notes = "")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("ad/proclamationList");
	}
	
	@ApiOperation(value = "分页查询信息", notes = "")
	@RequestMapping(value = "/getProclamationList", method = RequestMethod.POST)
	public PageResult<Proclamation> getProclamationList(PageRequest pageRequest) {
		Page<Proclamation> page = new Page<Proclamation>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		proclamationService.selectPage(page);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	@ApiOperation(value = "增加公告", notes = "")
	@RequestMapping(value = "/addProclamation", method = RequestMethod.POST)
	public JsonResult<String> addProclamation(Proclamation requestBody){
		boolean result = proclamationService.insert(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("增加公告失败");
		}
	}
	
	@ApiOperation(value = "删除公告", notes = "")
	@RequestMapping(value = "/delProclamation", method = RequestMethod.POST)
	public JsonResult<String> delProclamation(@RequestParam(value = "id", required = true) Integer id){
		boolean result =proclamationService.deleteById(id);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("删除公告失败");
		}
	}
	
	@ApiOperation(value = "修改公告", notes = "")
	@RequestMapping(value = "/updateProclamation", method = RequestMethod.POST)
	public JsonResult<String> updateProclamation(Proclamation requestBody){
		
		if(requestBody.getClickResult() == 1){
			Integer h5Id = requestBody.getH5Id();
			HtmlArticle html = htmlArticleService.selectById(h5Id);
			if(html == null){
				return JsonResult.buildError("没有找到H5页面");
			}
		}else{
			requestBody.setH5Id(0);
		}
		
		boolean result = proclamationService.updateById(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("更新公告失败");
		}
	}
}
