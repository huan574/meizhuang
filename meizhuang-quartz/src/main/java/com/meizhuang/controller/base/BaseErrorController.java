package com.meizhuang.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meizhuang.aspect.SignIgnore;
import com.meizhuang.aspect.TokenIgnore;
import com.meizhuang.result.JsonResult;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class BaseErrorController implements ErrorController {

	@SignIgnore
	@TokenIgnore
	@RequestMapping("/error")
	public <T> JsonResult<T> handleError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String msg;
		if (statusCode == null) {
			statusCode = 500;
		}
		if (statusCode == 404) {
			msg = "找不到你了呢";
		} else {
			msg = "好像出错了呢";
		}
		return JsonResult.buildError(statusCode.intValue(), msg);
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
