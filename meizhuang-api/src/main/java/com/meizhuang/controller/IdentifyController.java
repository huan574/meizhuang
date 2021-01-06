package com.meizhuang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.IdentifyInfo;
import com.meizhuang.service.IdentifyInfoService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IdentifyController extends BaseController{

	@Autowired
	private IdentifyInfoService identifyInfoService;
	
	
	@RequestMapping(value = "/identifyInfo")
	public ModelAndView identifyInfo(HttpServletRequest request, HttpServletResponse response) {
		
		EntityWrapper<IdentifyInfo> wrapper = new EntityWrapper<IdentifyInfo>();
		wrapper.orderBy("order_num", false);
		List<IdentifyInfo> list = identifyInfoService.selectList(wrapper);
		request.setAttribute("identify_list", list);
		return new ModelAndView("identify");
	}
}
