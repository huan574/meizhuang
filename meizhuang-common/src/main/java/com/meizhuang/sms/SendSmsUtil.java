package com.meizhuang.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meizhuang.sms.channel.cmcc.CMCCSendSms;
import com.meizhuang.sms.channel.nexmo.NEXMOSendSms;
import com.meizhuang.sms.enums.SmsTunnel;
import com.meizhuang.sms.enums.TunnelsEnum;
import com.meizhuang.sms.result.SmsResult;

@Component
public class SendSmsUtil {

	private static List<SmsTunnel> tunnels = new ArrayList<SmsTunnel>();

	static {
		tunnels.add(new SmsTunnel(1001, "SZ_SMS_CMCC", "CMCC_COMMON", 1002));
		tunnels.add(new SmsTunnel(1002, "NEXMO_SMS", "NEXMO_COMMON", 1002));
	}

	@Value("${sms.send.state: false}")
	private boolean sendState;
	@Value("${sms.send.sign}")
	private String sign;

	private static SendSmsUtil sendSmsUtil;

	@PostConstruct
	public void init() {
		sendSmsUtil = this;
		sendSmsUtil.sendState = this.sendState;
		sendSmsUtil.sign = this.sign;
	}

	public static SmsResult sendSms(String countryCode, String mobile, String content) {
		String sign = sendSmsUtil.sign;
		if (!sendSmsUtil.sendState) {
			return new SmsResult(true, "短信发送成功(测试)");
		}
		if (StringUtils.isEmpty(sign)) {
			return new SmsResult(true, "短信发送失败:未配置短信签名");
		}
		if (tunnels == null || tunnels.size() == 0) {
			return new SmsResult(false, "短信发送失败:未配置短信通道");
		} else {
			List<SmsTunnel> intertunnels = new ArrayList<SmsTunnel>();// 国内通道
			List<SmsTunnel> outertunnels = new ArrayList<SmsTunnel>();// 国外通道
			for (SmsTunnel tunnel : tunnels) {
				if (tunnel.getTunnelType() == 1001) {
					intertunnels.add(tunnel);
				} else {
					outertunnels.add(tunnel);
				}
			}
			// 国内
			if ("86".equals(countryCode)) {
				int len = intertunnels.size();
				if (len == 0) {
					return new SmsResult(false, "短信发送失败:未配置国内短信通道");
				}
				Random rand = new Random();
				int randNum = rand.nextInt(len);
				SmsTunnel curTunnel = intertunnels.get(randNum);
				if (TunnelsEnum.SZ_SMS_CMCC.getNumber().equals(curTunnel.getTunnelNum())) {
					if (1001 == curTunnel.getIsMultipleSign()) {// 单签名
						return CMCCSendSms.getInstance().sendSms(mobile, content, curTunnel.getAccount());
					} else if (1002 == curTunnel.getIsMultipleSign()) {// 多签名
						StringBuilder builder = new StringBuilder();
						builder.append("【");
						builder.append(sign);
						builder.append("】");
						builder.append(content);
						return CMCCSendSms.getInstance().sendSms(mobile, builder.toString(), curTunnel.getAccount());
					}
				} else {
					return new SmsResult(false, "短信发送失败:国内短信通道编号错误");
				}
			} else {// 国外
				if (outertunnels.size() == 0) {
					return new SmsResult(false, "短信发送失败:未配置国外短信通道");
				} else {
					int len = outertunnels.size();
					Random rand = new Random();
					int randNum = rand.nextInt(len);
					SmsTunnel curTunnel = outertunnels.get(randNum);
					if (TunnelsEnum.NEXMO_SMS.getNumber().equals(curTunnel.getTunnelNum())) {
						StringBuilder builder = new StringBuilder();
						builder.append("【");
						builder.append(sign);
						builder.append("】");
						builder.append(content);
						return NEXMOSendSms.getInstance().sendSms(countryCode + mobile, builder.toString(), curTunnel.getAccount(), sign);
					} else {
						return new SmsResult(false, "短信发送失败:国外短信通道编号错误");
					}
				}
			}
		}
		return new SmsResult(false, "短信发送失败");
	}

}
