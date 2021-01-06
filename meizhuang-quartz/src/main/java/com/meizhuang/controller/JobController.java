package com.meizhuang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meizhuang.base.BaseController;
import com.meizhuang.result.JsonResult;

@RestController
public class JobController extends BaseController {

	@RequestMapping(value = "/")
	public JsonResult<Object> welcome() {
		return JsonResult.buildSuccess();
	}

}
