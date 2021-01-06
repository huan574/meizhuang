package com.meizhuang.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.hutool.json.JSONUtil;

import com.authority.app.session.AdminSession;
import com.meizhuang.aspect.SessionIgnore;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.utils.redis.RedisUtil;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {
		
		
		
		/**
		//并发处理
		String url = request.getRequestURI();
		boolean isReadInterface = url.contains("list") || url.contains("get");
		if(!isReadInterface) {
			StringBuilder sbParam = new StringBuilder();
			Map<String, String[]> parameterMap = request.getParameterMap();
			for(Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
				String[] vals = parameter.getValue();
				for(String val : vals) {
					sbParam.append(val).append("_");
				}
			}
			
			String key = url + sbParam.toString();
			RedisUtil.delete(key);
		}**/
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		
		
		if (session != null && session.getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID) != null) {
			
			return true;
		} else {
			SessionIgnore ignore;
			if (handler instanceof HandlerMethod) {
				ignore = ((HandlerMethod) handler).getMethodAnnotation(SessionIgnore.class);
				if (ignore != null) {
					return true;
				}
			}
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax超时处理
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(JSONUtil.toJsonStr(PageResult.buildError(BizExceptionEnum.SESSION_TIMEOUT.getCode(), BizExceptionEnum.SESSION_TIMEOUT.getMessage())));
				response.getWriter().flush();
				response.getWriter().close();
			} else {
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
				// http超时的处理
				response.sendRedirect(basePath + "loginRedirect");
			}
			return false;
		}
		
		/*if (session != null && session.getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID) != null) {
			//并发处理
			boolean isReadInterface = url.contains("list") || url.contains("get");
			if(!isReadInterface) {
				
				
				StringBuilder sbParam = new StringBuilder();
				Map<String, String[]> parameterMap = request.getParameterMap();
				for(Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
					String[] vals = parameter.getValue();
					for(String val : vals) {
						sbParam.append(val).append("_");
					}
				}
				String key = url + sbParam.toString();
				JsonResult<Object> jsonResult=  RedisUtil.get(key);
				if(jsonResult.isSuccess() && jsonResult.getData() != null) {
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(JSONUtil.toJsonStr(PageResult.buildError(BizExceptionEnum.CONCURRENT_REQUESTS.getCode(), BizExceptionEnum.CONCURRENT_REQUESTS.getMessage())));
					response.getWriter().flush();
					response.getWriter().close();
					return false;
				}
				
				RedisUtil.set(key, "concurrent", 5);
			}
			
			
			return true;
		} else {
			SessionIgnore ignore;
			if (handler instanceof HandlerMethod) {
				ignore = ((HandlerMethod) handler).getMethodAnnotation(SessionIgnore.class);
				if (ignore != null) {
					return true;
				}
			}
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax超时处理
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(JSONUtil.toJsonStr(PageResult.buildError(BizExceptionEnum.SESSION_TIMEOUT.getCode(), BizExceptionEnum.SESSION_TIMEOUT.getMessage())));
				response.getWriter().flush();
				response.getWriter().close();
			} else {
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
				// http超时的处理
				response.sendRedirect(basePath + "loginRedirect");
			}
			return false;
		}*/
	}

}
