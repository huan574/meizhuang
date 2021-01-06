package com.meizhuang.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.authority.app.session.AdminSession;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.SystemLog;
import com.meizhuang.utils.SystemLogUtils;
import com.meizhuang.utils.support.NetworkUtils;

@Aspect
@Component
public class SystemOperationLogAspect extends BaseController {

	@Pointcut("@annotation(com.meizhuang.aspect.SystemOperationLog)")
	public void systemOperationLog() {

	}

	/**
	 * 环绕增强，相当于MethodInterceptor
	 */
	@Around("systemOperationLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object res = null;
		long time = System.currentTimeMillis();
		try {
			res = joinPoint.proceed();
			time = System.currentTimeMillis() - time;
			return res;
		} finally {
			// 方法执行完成后增加日志
			addOperationLog(joinPoint, res, time);
		}
	}

	private void addOperationLog(JoinPoint joinPoint, Object res, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SystemLog log = new SystemLog();
		SystemOperationLog annotation = signature.getMethod().getAnnotation(SystemOperationLog.class);
		if (annotation != null) {
			log.setOperationType(annotation.operationType().getValue());
			log.setContent(getContent(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs(), annotation));
		}
		HttpServletRequest request = super.getHttpServletRequest();
		log.setUrl(request.getRequestURI());
		log.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
		log.setParams(JSON.toJSONString(joinPoint.getArgs()));
		log.setReturnValue(JSON.toJSONString(res));
		log.setRunTime(time);
		log.setIp(NetworkUtils.getIp());
		Object object = request.getSession().getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID);
		if (object != null) {
			AdminSession adminSession = (AdminSession) object;
			log.setCman(adminSession.getUsername());
		} else {
			log.setCman("未登录");
		}
		SystemLogUtils.addLog(log);
	}

	/**
	 * 对当前登录用户和占位符处理
	 * 
	 * @param argNames方法参数名称数组
	 * @param args方法参数数组
	 * @param annotation注解信息
	 * @return 返回处理后的描述
	 */
	private String getContent(String[] argNames, Object[] args, SystemOperationLog annotation) {
		Map<Object, Object> map = new HashMap<Object, Object>(4);
		for (int i = 0; i < argNames.length; i++) {
			map.put(argNames[i], args[i]);
		}
		String content = annotation.value();
		try {
			content = annotation.value();
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				Object k = entry.getKey();
				Object v = entry.getValue();
				content = content.replace("{{" + k + "}}", JSON.toJSONString(v));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

}