package com.meizhuang.utils.encrypt;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class SignUtils {

	/**
	 * 使用MD5算法生成签名结果,规则是:按参数名称ascii 码从小到大排序拼接,遇到空值的参数不参加签名。
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String buildSign(Map<String, String> paramMap) {
		Map<String, String> map = new TreeMap<String, String>(paramMap);
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			if (StringUtils.isNotEmpty(v)) {
				sb.append(k).append("=").append(v).append("&");
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		return MD5.MD5EncodeUTF8(sb.toString());
	}

	public static String buildSign(Map<String, String> paramMap, String keyName, String keyValue) {
		Map<String, String> map = new TreeMap<String, String>(paramMap);
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			if (StringUtils.isNotEmpty(v)) {
				sb.append(k).append("=").append(v).append("&");
			}
		}
		if (StringUtils.isNotEmpty(keyName) && StringUtils.isNotEmpty(keyValue)) {
			sb.append(keyName + "=" + keyValue);
		} else {
			sb.delete(sb.length() - 1, sb.length());
		}
		System.out.println(sb.toString());
		return MD5.MD5EncodeUTF8(sb.toString());
	}

	public static boolean checkSign(Map<String, String> paramMap, String sign) {
		String tempSign = buildSign(paramMap);
		if (StringUtils.isEmpty(tempSign) || StringUtils.isEmpty(sign)) {
			return false;
		}
		if (!tempSign.equalsIgnoreCase(sign)) {
			return false;
		}
		return true;
	}

}
