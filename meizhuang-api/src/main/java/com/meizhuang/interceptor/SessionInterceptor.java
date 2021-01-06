package com.meizhuang.interceptor;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.hutool.json.JSONUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.aspect.SessionPrivilege;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.UserInfoService;
import com.meizhuang.utils.SystemParameterUtils;
import com.meizhuang.utils.redis.RedisUtil;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {
		/**
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

	
	public String getCookiesValue(String cookieName, HttpServletRequest request){
		String value = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(cookieName)){
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		
		
		Object getSiteName = request.getSession().getAttribute("SYSTEM_SITE_NAME");
		if(getSiteName == null){
			String siteName = SystemParameterUtils.get(SystemParameterEnum.SYSTEM_SITE_NAME);
			 request.getSession().setAttribute("SYSTEM_SITE_NAME", siteName);
		}
		

		Object getSiteDesc = request.getSession().getAttribute("SYSTEM_SITE_DESC");
		if(getSiteDesc == null){
			String siteDesc = SystemParameterUtils.get(SystemParameterEnum.SYSTEM_SITE_DESC);
			 request.getSession().setAttribute("SYSTEM_SITE_DESC", siteDesc);
		}
		
		Object getCacheControl = request.getSession().getAttribute("WEB_CACHE_CONTROL");
		if(getCacheControl == null){
			String cacheControl = SystemParameterUtils.get(SystemParameterEnum.WEB_CACHE_CONTROL);
			 request.getSession().setAttribute("WEB_CACHE_CONTROL", cacheControl);
		}
		
		Object getTip1Title = request.getSession().getAttribute("HOME_TIP1_TITLE");
		if(getTip1Title == null){
			String homeTip1Title = SystemParameterUtils.get(SystemParameterEnum.HOME_TIP1_TITLE);
			request.getSession().setAttribute("HOME_TIP1_TITLE", homeTip1Title);
		}
		
		Object user = request.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		String cookiesName = getCookiesValue(UserInfo.USER_COOKIES_NAME, request) ;
		if(user==null && StringUtils.isNotBlank(cookiesName)){
			EntityWrapper<UserInfo> entity = new EntityWrapper<UserInfo>();
			entity.eq("login_cookies", cookiesName);
			UserInfo userInfo = userInfoService.selectOne(entity);
			if(userInfo != null){
				request.getSession().setAttribute(UserInfo.USER_SESSION_UID, userInfo.getUid());
				request.setAttribute("user", userInfo);
			}
		}
		
		
		SessionPrivilege privilege;
		
		if (handler instanceof HandlerMethod) {
			privilege = ((HandlerMethod) handler).getMethodAnnotation(SessionPrivilege.class);
			if (privilege == null) {
				
				return true;
				
			}
			
			if (session == null || session.getAttribute(UserInfo.USER_SESSION_UID) == null) {
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax超时处理
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(JSONUtil.toJsonStr(PageResult.buildError(BizExceptionEnum.SESSION_TIMEOUT.getCode(), BizExceptionEnum.SESSION_TIMEOUT.getMessage())));
					response.getWriter().flush();
					response.getWriter().close();
				} else {
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
					// http超时的处理
					response.sendRedirect(basePath + "login");
				}
			}
			return false;
		}
		
		return true;
		
	}

}
