package com.meizhuang.utils.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.util.StringUtils;
import com.meizhuang.result.JsonResult;

public class HttpClientUtil {

	private static final String CHARSET = "GBK";

	public static JsonResult<String> sendPaymentPostByXML(String url, String xmlStr) {
		return sendPaymentPostByXML(url, xmlStr, CHARSET);
	}

	public static JsonResult<String> sendPaymentPostByXML(String url, String xmlStr, String charset) {
		// 创建默认的httpClient实例.
		HttpClient httpclient = HttpClientBuilder.create().build();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		try {
			StringEntity reqEntity = new StringEntity(xmlStr, charset);
			httppost.setEntity(reqEntity);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity, charset);
				if (StringUtils.isEmpty(content)) {
					return JsonResult.buildError("请求接口返回信息为空");
				}
				return JsonResult.buildSuccess(content);
			} else {
				return JsonResult.buildError("请求接口返回信息为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildError("接口请求失败");
		} finally {
			httppost.releaseConnection();
		}
	}

	public static JsonResult<String> sendPaymentPostByParams(String url, Map<String, String> mapParams) {
		return sendPaymentPostByParams(url, mapParams, CHARSET);
	}

	public static JsonResult<String> sendPaymentPostByParams(String url, Map<String, String> mapParams, String charset) {
		// 构造参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String, String>> entries = mapParams.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		// 创建默认的httpClient实例.
		HttpClient httpclient = HttpClientBuilder.create().build();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		try {
			StringEntity reqEntity = new UrlEncodedFormEntity(params, charset);
			httppost.setEntity(reqEntity);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity, charset);
				if (StringUtils.isEmpty(content)) {
					return JsonResult.buildError("请求接口返回信息为空");
				}
				return JsonResult.buildSuccess(content);
			} else {
				return JsonResult.buildError("请求接口返回信息为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildError("接口请求失败");
		} finally {
			httppost.releaseConnection();
		}
	}

	public static JsonResult<String> sendPaymentPostByJSON(String url, String jsonStr) {
		return sendPaymentPostByJSON(url, jsonStr, CHARSET);

	}

	public static JsonResult<String> sendPaymentPostByJSON(String url, String jsonStr, String charset) {
		// 创建默认的httpClient实例.
		HttpClient httpclient = HttpClientBuilder.create().build();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		try {
			StringEntity reqEntity = new StringEntity(jsonStr, charset);
			httppost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			httppost.setEntity(reqEntity);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return JsonResult.buildSuccess(EntityUtils.toString(entity, charset));
			} else {
				return JsonResult.buildError("请求接口返回信息为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildError("接口请求失败");
		} finally {
			httppost.releaseConnection();
		}
	}

	public static JsonResult<String> sendGet(String url, Map<String, String> parameters, String charset) {
		String result = "";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params;// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), charset));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), charset)).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			String full_url = url + "?" + params;
			URL realUrl = new URL(full_url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 必须设置false，否则会自动redirect到重定向后的地址
			// connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			return JsonResult.buildSuccess(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildError("接口请求失败");
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
