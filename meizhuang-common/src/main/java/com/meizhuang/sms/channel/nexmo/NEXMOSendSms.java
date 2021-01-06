package com.meizhuang.sms.channel.nexmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import com.meizhuang.sms.channel.nexmo.enums.NexmoEnum;
import com.meizhuang.sms.result.SmsResult;

public class NEXMOSendSms {

	private static final String LANGUAGE = "&type=unicode";
	private static final String NEXMO_URL = "https://rest.nexmocn.com/sms/json";

	private NEXMOSendSms() {

	}

	public static NEXMOSendSms getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	private static enum Singleton {
		INSTANCE;

		private NEXMOSendSms singleton;

		private Singleton() {
			singleton = new NEXMOSendSms();
		}

		public NEXMOSendSms getInstance() {
			return singleton;
		}
	}

	public SmsResult sendSms(String phone, String text, String account, String sign) {
		NexmoEnum ne = NexmoEnum.valueOf(account);
		if (ne == null) {
			return new SmsResult(false, "短信发送失败:通道账号不存在");
		} else {
			return sendSms(phone, text, ne.getKey(), ne.getSecret(), sign);
		}
	}

	public SmsResult sendSms(String phone, String text, String key, String secret, String sign) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = NEXMO_URL + "?" + "api_key=" + key + "&api_secret=" + secret + "&to=" + phone + "&from=" + sign + "&text=" + URLEncoder.encode(text, "UTF-8") + LANGUAGE;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			trustAllHosts(conn);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			JSONObject obj = new JSONObject(result);
			JSONArray js = obj.getJSONArray("messages");
			JSONObject objs = new JSONObject(js.get(0).toString());
			if (!objs.get("status").equals("0")) {
				return new SmsResult(false, "短信发送失败:发送请求异常");
			} else {
				return new SmsResult(true, "短信发送成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new SmsResult(false, "短信发送失败:发送请求异常");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 覆盖java默认的证书验证
	 */
	private static final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[] {};
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}
	} };

	/**
	 * 信任所有
	 * 
	 * @param connection
	 * @return
	 */
	private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
		SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(null, trustAllCerts, new SecureRandom());
			SSLSocketFactory newFactory = sc.getSocketFactory();
			connection.setSSLSocketFactory(newFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oldFactory;
	}

}
