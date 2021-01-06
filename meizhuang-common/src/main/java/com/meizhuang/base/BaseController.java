package com.meizhuang.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.authority.app.session.AdminSession;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.utils.support.HttpKit;

public class BaseController {

	protected static String SUCCESS = "SUCCESS";
	protected static String ERROR = "ERROR";

	protected static String REDIRECT = "redirect:";
	protected static String FORWARD = "forward:";

	protected HttpServletRequest getHttpServletRequest() {
		return HttpKit.getRequest();
	}

	protected HttpServletResponse getHttpServletResponse() {
		return HttpKit.getResponse();
	}

	protected HttpSession getSession() {
		return HttpKit.getRequest().getSession();
	}

	protected HttpSession getSession(Boolean flag) {
		return HttpKit.getRequest().getSession(flag);
	}

	protected String getParameter(String name) {
		return HttpKit.getRequest().getParameter(name);
	}

	protected void setAttr(String name, Object value) {
		HttpKit.getRequest().setAttribute(name, value);
	}

	protected Integer getSystemInvokCount() {
		return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
	}

	/**
	 * 包装一个list，让list增加额外属性
	 */
	protected Object warpObject(BaseControllerWarpper warpper) {
		return warpper.warp();
	}

	/**
	 * 删除cookie
	 */
	protected void deleteCookieByName(String cookieName) {
		Cookie[] cookies = this.getHttpServletRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				Cookie temp = new Cookie(cookie.getName(), "");
				temp.setMaxAge(0);
				this.getHttpServletResponse().addCookie(temp);
			}
		}
	}

	/**
	 * 获取后台登录用户名
	 */
	protected static String getUserName() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object object = request.getSession().getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID);
		if (object != null) {
			AdminSession adminSession = (AdminSession) object;
			return adminSession.getUsername();
		} else {
			return "未登录";
		}
	}
	
	
	
	/**
     * 获取访问者真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length()
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
	
    
  //获得数字串工具类
    public static List<String> getFullNumFromString(String str){
        List<String> resultList = new ArrayList<>();
        StringBuilder numBuilder = new StringBuilder();
        str = str.replaceAll(" ","");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 48 && ch <= 57) {
                numBuilder.append(ch);
                if ( i == str.length() -1) {
                    resultList.add(numBuilder.toString());
                }
            } else {
                if (!numBuilder.toString().equals("") && numBuilder.length() > 0) {
                    resultList.add(numBuilder.toString());
                    numBuilder = new StringBuilder();
                }
            }
        }
        return resultList;
    }
    
    
    /**
    * 保存Cookie到客户端
    */
    public static void saveCookie(HttpServletResponse response, String cookieKey, String value, int time) {

	    // 开始保存Cookie（cookie是网站名和值）
	    Cookie cookie = new Cookie(cookieKey, value);
	    // 8Day
	    cookie.setMaxAge(time);
	    // cookie有效路径是网站根目录
	    cookie.setPath("/");
	    // 向客户端写入
	    response.addCookie(cookie);
    }

}
