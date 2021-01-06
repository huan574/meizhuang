package com.meizhuang.web;

import java.text.SimpleDateFormat;

/**
 * 常量信息
 * @author caidian
 *
 */
public abstract class Constants {
	
	/*密码加密固定值*/
	public static final String PWD_MD5_KEY = "meizhuang2020!@#";
	
	/*token加密固定值*/
	public static final String TOEKN_MD5_KEY = "meizhuang@meizhuang2020";
	
	/*修改手机号验证固定值*/
	public static final String MODIFY_MOBILE_NUMBER_KEY = "MODIFY_MOBILE_NUMBER_KEY_";
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
}
