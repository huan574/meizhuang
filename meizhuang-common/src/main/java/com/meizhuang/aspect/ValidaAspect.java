package com.meizhuang.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;

@Aspect
@Component
public class ValidaAspect {
	
	@Around("execution(public com.meizhuang.result.JsonResult com.meizhuang.controller.*.*(..)) && args(..,bindingResult)")
	public Object jsonResultAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				if ("typeMismatch".equals(fieldError.getCode())) {
					return JsonResult.buildError(400, "参数" + fieldError.getRejectedValue() + "格式错误");
				}
				return JsonResult.buildError(400, fieldError.getDefaultMessage());
			}
			return JsonResult.buildError(BizExceptionEnum.PARAM_ERROR);
		} else {
			return pjp.proceed();
		}
	}

	@Around("execution(public com.meizhuang.result.PageResult com.meizhuang.controller.*.*(..)) && args(..,bindingResult)")
	public Object pageResultAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				if ("typeMismatch".equals(fieldError.getCode())) {
					return PageResult.buildError(400, "参数" + fieldError.getRejectedValue() + "格式错误");
				}
				return PageResult.buildError(400, fieldError.getDefaultMessage());
			}
			return PageResult.buildError(BizExceptionEnum.PARAM_ERROR);
		} else {
			return pjp.proceed();
		}
	}

}
