package com.meizhuang.utils.encrypt;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.util.StringUtils;

public class MD5 {

	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 
	 * @param map中不包含非参与签名字段
	 * @param key_name 密钥名称
	 * @param api_key 密钥
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String encodeUTF8(Map map, String key_name, String api_key) {
		try {
			if (map == null || map.size() == 0) {
				return null;
			}
			TreeMap treeMap = new TreeMap();
			treeMap.putAll(map);
			StringBuilder sb = new StringBuilder();
			for (Object key : treeMap.keySet()) {
				Object value = treeMap.get(key);
				if (!StringUtils.isEmpty(value)) {
					sb.append(key).append("=").append(value).append("&");
				}
			}
			// 拼接api_key
			sb.append(key_name).append("=").append(api_key);
			return MD5EncodeUTF8(sb.toString());
		} catch (Exception ex) {
			return null;
		}
	}

	public static String encode(String origin, String charset) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
		} catch (Exception ex) {
			return null;
		}
		return resultString;
	}

	public static String MD5EncodeGB2312(String origin) {
		return encode(origin, "GB2312");
	}

	public static String MD5EncodeGBK(String origin) {
		return encode(origin, "GBK");
	}

	public static String MD5EncodeUTF8(String origin) {
		return encode(origin, "UTF-8");
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}