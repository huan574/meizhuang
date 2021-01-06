package com.meizhuang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.hutool.json.JSONUtil;

import com.authority.app.http.handler.Authority;
import com.authority.app.session.AdminSession;
import com.meizhuang.aspect.AuthIgnore;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.result.PageResult;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthIgnore ignore;
		if (handler instanceof HandlerMethod) {
			ignore = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
			if (ignore != null) {
				return true;
			}
		}
		String url = request.getRequestURI();
		AdminSession adminSession = (AdminSession) request.getSession().getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID);
		boolean b = Authority.hasPermission(adminSession.getId(), url);
		if (!b) {
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(JSONUtil.toJsonStr(PageResult.buildError(BizExceptionEnum.NO_OPERATION_POWER.getCode(), BizExceptionEnum.NO_OPERATION_POWER.getMessage())));
				response.getWriter().flush();
				response.getWriter().close();
			} else {
				request.getRequestDispatcher("/power").forward(request, response);
			}
			return false;
		}
		return true;
	}

}
