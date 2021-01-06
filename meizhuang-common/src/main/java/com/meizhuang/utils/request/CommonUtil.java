package com.meizhuang.utils.request;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;


/**
 * 此处为工具类(暂定)
 * @author swd1
 */
public class CommonUtil {

	@SuppressWarnings("rawtypes")
	public static Map<String, String> requestToMap(ServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] values = request.getParameterValues(name);
			if (values.length == 1) {
				map.put(name, values[0]);
			}
		}
		return map;
	}
}
