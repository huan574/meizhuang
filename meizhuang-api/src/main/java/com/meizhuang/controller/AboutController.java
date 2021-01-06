package com.meizhuang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.meizhuang.base.BaseController;

import springfox.documentation.annotations.ApiIgnore;





@ApiIgnore
@RestController
public class AboutController extends BaseController {

	@RequestMapping(value = "/about")
	public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("about/about");
	}
	
	@RequestMapping(value = "/business")
	public ModelAndView business(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("about/business");
	}
	
	@RequestMapping(value = "/service")
	public ModelAndView service(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("about/service");
	}
	
	@RequestMapping(value = "/law")
	public ModelAndView law(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("about/law");
	}
	
	@RequestMapping(value = "/merchant")
	public ModelAndView merchant(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("shangjiaqun");
	}
	
}
