package com.meizhuang.config.ds;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {

	@Value("${druid.login.username}")
	private String loginUsername;

	@Value("${druid.login.password}")
	private String loginPassword;

	/**
	 * 注册Servlet信息， 配置监控视图
	 */
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean druidServlet() {
		String ip;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString(); // 获取本机ip
		} catch (UnknownHostException e) {
			ip = "127.0.0.1";
		}
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 白名单
		servletRegistrationBean.addInitParameter("allow", ip);
		// IP黑名单(存在共同时，deny优先于allow)
		servletRegistrationBean.addInitParameter("deny", "");
		// 登录查看信息的账号密码,用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
		servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}

	/**
	 * 注册Filter信息, 监控拦截器
	 */
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}
