package com.meizhuang.sms.channel.cmcc;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.json.JSONObject;

import com.meizhuang.result.JsonResult;
import com.meizhuang.sms.channel.cmcc.enums.CmccEnum;
import com.meizhuang.sms.channel.cmcc.enums.ReturnCode;
import com.meizhuang.sms.result.SmsResult;
import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.utils.request.HttpClientUtil;

public class CMCCSendSms {

	private static final String CMCC_SMS_IP = "11.11.11.11";
	private static String reqUrl = new StringBuilder("http://").append(CMCC_SMS_IP).append(":8080").toString();// 请求路径

	private CMCCSendSms() {
	}

	public static CMCCSendSms getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private CMCCSendSms singleton;

		private Singleton() {
			singleton = new CMCCSendSms();
		}

		public CMCCSendSms getInstance() {
			return singleton;
		}
	}

	public SmsResult sendSms(String mobile, String content, String account) {
		CmccEnum cm = CmccEnum.valueOf(account);
		if (cm == null) {
			return new SmsResult(false, "短信发送失败:通道账号不存在");
		} else {
			return sendSms(mobile, content, cm.getAccount(), cm.getPassword());
		}
	}

	public SmsResult sendSms(String mobile, String content, String account, String password) {
		String digest = MD5.MD5EncodeUTF8(mobile + content);// 验签

		Map<String, String> mapParams = new HashMap<String, String>();
		mapParams.put("account", account);
		mapParams.put("passwd", password);
		mapParams.put("mobiles", mobile);
		mapParams.put("content", content);
		mapParams.put("01", "");
		mapParams.put("batchno", "");
		mapParams.put("digest", digest);

		String url = new StringBuilder(String.valueOf(reqUrl)).append("/sms/sendsms.jsp").toString();
		JsonResult<String> result = HttpClientUtil.sendPaymentPostByParams(url, mapParams, "UTF-8");
		if (!result.isSuccess()) {
			return new SmsResult(false, "短信发送失败:发送请求错误");
		}
		String msg = result.getMsg();
		JSONObject json = new JSONObject(msg);
		String backResult = ReturnCode.getMsgByCode(json.get("code").toString()); // 保存短信发送日志
		if (backResult.contains("短信发送成功")) {
			return new SmsResult(true, "短信发送成功");
		} else {
			return new SmsResult(false, "短信发送失败:" + backResult);
		}
	}

}
