package com.meizhuang.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.meizhuang.base.BaseController;
import com.meizhuang.config.WebMvcConfig;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.SysExceptionUtils;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@ControllerAdvice
@ResponseBody
public class ExceptionThrowableHandler extends BaseController {

	private Logger log = LoggerFactory.getLogger(ExceptionThrowableHandler.class);
	
	@ExceptionHandler(Throwable.class)
	public <T> JsonResult<T> throwable() {
		log.error("出错啦，请稍候重试哦throwable");
		return JsonResult.buildError(BizExceptionEnum.SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public <T> JsonResult<T> exception(Exception e) {
		log.error(e.toString());
		e.printStackTrace();
		SysExceptionUtils.addSysException(WebMvcConfig.USE_SYSTEM, e); // 添加异常日志
		// 判断异常类型
		if (e instanceof MethodArgumentTypeMismatchException) {
			return JsonResult.buildError(BizExceptionEnum.PARAM_FORMAT_ERROR);
		} else if (e instanceof MultipartException) {
			return JsonResult.buildError(BizExceptionEnum.FILE_MAX_SIZE_ERROR);
		} else if (e instanceof BussinessException) {
			BussinessException bussinessException = (BussinessException) e;
			return JsonResult.buildError(bussinessException.getCode(), bussinessException.getMessage());
		}
		return JsonResult.buildError(BizExceptionEnum.SERVER_ERROR);
	}

}
