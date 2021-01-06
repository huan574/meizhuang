package com.meizhuang.interceptor;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meizhuang.aspect.SignIgnore;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.encrypt.SignUtils;

import cn.hutool.json.JSONUtil;

@Component
public class SystemInterceptor implements HandlerInterceptor {

	private static String SECRET_KEY = "5CE1B6FF37F8E9AB8E336A23A83E03A0";

	@Value("${spring.profiles.active}")
	private String profiles;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		if ("dev".equals(profiles)) { // 开发环境
//			return true;
//		}
		SignIgnore ignore;
		if (handler instanceof HandlerMethod) {
			ignore = ((HandlerMethod) handler).getMethodAnnotation(SignIgnore.class);
			if (ignore != null) {
				return true;
			}
		}
		
		String path=request.getRequestURI();
		
		//版本更新接口直接返回true
//		if(path.contains("version/check") ) {
//			return true;
//		}
		String appId = request.getHeader("x-meizhuang-appId"); // 应用ID
		String timestamp = request.getHeader("x-meizhuang-timestamp"); // 时间戳
		String nonce = request.getHeader("x-meizhuang-nonce");// 唯一随机字符串
		String sign = request.getHeader("x-meizhuang-sign"); // 生成的签名

		String phoneSystemModel = request.getHeader("x-meizhuang-system-model");
		String imei = request.getHeader("x-meizhuang-imei");
		String deviceBrand = request.getHeader("x-meizhuang-device-brand");
		String deviceName= request.getHeader("x-meizhuang-device-name");
		
		if (!NumberUtils.isNumber(appId) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(sign)) {
			outJson(JsonResult.buildError(BizExceptionEnum.ILLEGAL_REQUESTS.getCode(), BizExceptionEnum.ILLEGAL_REQUESTS.getMessage()), response);
			return false;
		}

		// 构造参数
		Map<String, String> map = new TreeMap<String, String>();
		map.put("x-meizhuang-appId", appId);
		map.put("x-meizhuang-timestamp", timestamp);
		map.put("x-meizhuang-nonce", nonce);
		map.put("x-meizhuang-system-model", phoneSystemModel);
		map.put("x-meizhuang-imei", imei);
		map.put("x-meizhuang-device-brand", deviceBrand);
		map.put("x-meizhuang-device-name", deviceName);
		
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		map.put("secretKey", SECRET_KEY);
		boolean signState = SignUtils.checkSign(map, sign);
		if (!signState) { // 验签失败
			outJson(JsonResult.buildError(BizExceptionEnum.ILLEGAL_REQUESTS), response);
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
