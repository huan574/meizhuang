package com.meizhuang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meizhuang.aspect.TokenIgnore;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.TokenService;

import cn.hutool.json.JSONUtil;

@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenService tokenService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		TokenIgnore ignore;
		if (handler instanceof HandlerMethod) {
			ignore = ((HandlerMethod) handler).getMethodAnnotation(TokenIgnore.class);
			if (ignore != null) {
				return true;
			}
		}
		String appId = request.getHeader("x-meizhuang-appId"); // 应用ID
		String token = request.getHeader("x-meizhuang-token");
		String phoneSystemModel = request.getHeader("x-meizhuang-system-model");
		String imei = request.getHeader("x-meizhuang-imei");
		String deviceBrand = request.getHeader("x-meizhuang-device-brand");
		String deviceName = request.getHeader("x-meizhuang-device-name");
		

		String uid = request.getParameter("uid"); // 用户ID
		
		
		if (!NumberUtils.isNumber(appId) || !NumberUtils.isNumber(uid)) {
			outJson(JsonResult.buildError(BizExceptionEnum.TOKEN_ERROR), response);
			return false;
		}
		if (StringUtils.isEmpty(token)) {
			outJson(JsonResult.buildError(BizExceptionEnum.TOKEN_ERROR), response);
			return false;
		}

		// 验证刷单用户的token
		JsonResult<Object> result = tokenService.validatorToken(Integer.parseInt(uid), Integer.parseInt(appId), 1, token,phoneSystemModel,imei,deviceBrand, deviceName);
		if (!result.isSuccess()) {
			outJson(result, response);
			return false;
		}
		return true;
	}

	private void outJson(JsonResult<?> result, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSONUtil.toJsonStr(result));
		response.getWriter().flush();
		response.getWriter().close();
	}

}
