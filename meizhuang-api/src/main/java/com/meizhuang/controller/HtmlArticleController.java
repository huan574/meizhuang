package com.meizhuang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.meizhuang.base.BaseController;
import com.meizhuang.entity.HtmlArticle;
import com.meizhuang.service.HtmlArticleService;

import springfox.documentation.annotations.ApiIgnore;

/**   

*/
@ApiIgnore
@RestController
public class HtmlArticleController extends BaseController {

	@Autowired
	private HtmlArticleService htmlArticleService;
	
	@RequestMapping(value = "/h5page/{id}")
	public ModelAndView h5page(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		
		if(StringUtils.isNotBlank(id)){
			HtmlArticle htmlArticle = htmlArticleService.selectById(id);
			if(htmlArticle != null){
				request.setAttribute("htmlArticle", htmlArticle);
			}
		}
		return new ModelAndView("html_article");
		
	}
}
