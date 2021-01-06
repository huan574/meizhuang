package com.meizhuang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.HtmlArticle;
import com.meizhuang.entity.Proclamation;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.HtmlArticleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/flow/html")
public class HtmlArticleController extends BaseController {

	@Autowired
	private HtmlArticleService htmlArticleService;
	

	
	@RequestMapping(value = "/htmlLarticleList")
	public ModelAndView htmlLarticleList(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("htmlarticle/htmlLarticleList"); 
	}
	
	@RequestMapping(value = "/addHtmlLarticlePage")
	public ModelAndView addHtmlLarticlePage(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		
		if(StringUtils.isNotBlank(id)){
			
			HtmlArticle htmlArticle = htmlArticleService.selectById(id);
			
			request.setAttribute("htmlArticle", htmlArticle);
			request.setAttribute("actionUrl", "/flow/html/updateHtmlArticle");
		}else{
			
			request.setAttribute("actionUrl", "/flow/html/addHtmlLarticleDo");
		}
		return new ModelAndView("htmlarticle/addHtmlLarticle");
	}
	
	@PostMapping(value = "/addHtmlLarticleDo")
	public ModelAndView addHtmlLarticleDo(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String[]>  paramMap = request.getParameterMap();
		
		String[] content1s= paramMap.get("content1");
		String content1 = request.getParameter("content1");
		
		if(content1s.length > 0){
			content1 = content1s[0].replace("embed", "video");
		}
		
		
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		
		if(StringUtils.isBlank(content1)){
			return new ModelAndView("htmlarticle/addHtmlLarticle");
		}
		
		HtmlArticle newHtml = new HtmlArticle();
		newHtml.setType(Integer.valueOf(type));
		newHtml.setTitle(title);
		newHtml.setText(content1);
		
		htmlArticleService.insert(newHtml);
		
		
		return new ModelAndView("redirect:/flow/html/htmlLarticleList");
	}
	
	
	
	@ApiOperation(value = "分页查询信息", notes = "")
	@RequestMapping(value = "/getHtmlArticleList", method = RequestMethod.POST)
	public PageResult<HtmlArticle> getHtmlArticleList(PageRequest pageRequest) {
		Page<HtmlArticle> page = new Page<HtmlArticle>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		htmlArticleService.selectPage(page);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
		
	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/delHtmlArticle", method = RequestMethod.POST)
	public JsonResult<String> delHtmlArticle(@RequestParam(value = "id", required = true) Integer id){
		boolean result =htmlArticleService.deleteById(id);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("删除失败");
		}
	}
	
	
	@ApiOperation(value = "更新", notes = "")
	@RequestMapping(value = "/updateHtmlArticle", method = RequestMethod.POST)
	public ModelAndView updateHtmlArticle(HtmlArticle requestBody, HttpServletRequest request){
		
		Map<String, String[]>  paramMap = request.getParameterMap();
		
		String[] content1s= paramMap.get("content1");
		String content1 = request.getParameter("content1");
		
		if(content1s.length > 0){
			content1 = content1s[0].replace("embed", "video");
		}
		requestBody.setText(content1);
		boolean result = htmlArticleService.updateById(requestBody);
		return new ModelAndView("redirect:/flow/html/htmlLarticleList");
	}
	
}
