package com.meizhuang.controller;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.authority.app.http.handler.Authority;
import com.authority.app.session.AdminSession;
import com.authority.app.view.Admin;
import com.authority.app.view.Menu;
import com.authority.app.view.result.AuthorityResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.aspect.SessionIgnore;
import com.meizhuang.aspect.SystemOperationLog;
import com.meizhuang.base.BaseController;
import com.meizhuang.config.WebMenus;
import com.meizhuang.entity.UserAdmin;
import com.meizhuang.entity.enums.SystemOperationType;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.UserAdminService;
import com.meizhuang.utils.common.IconClass;
import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.utils.encrypt.PasswordRSAUtil;
import com.meizhuang.web.Constants;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.json.JSONUtil;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class LoginController extends BaseController {

	@Autowired
	private UserAdminService userAdminService;

	@SessionIgnore
	@RequestMapping(value = "/")
	public ModelAndView welcome() {
		return new ModelAndView("login/login");
	}

	@SessionIgnore
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login/login");
	}

	@SessionIgnore
	@RequestMapping(value = "loginRedirect")
	public ModelAndView loginRedirect() {
		return new ModelAndView("login/loginRedirect");
	}

	/**
	 * 获取RSA 公共key
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SessionIgnore
	@RequestMapping(value = "/getPublicKey")
	public JsonResult<Map<String, String>> getPublicKey() throws Exception {
		RSAPublicKey publicKey = PasswordRSAUtil.getPublicKey();
		String modulus = publicKey.getModulus().toString(16);// 模
		String exponent = publicKey.getPublicExponent().toString(16);// 公钥指数
		Map<String, String> map = new HashMap<String, String>();
		map.put("modulus", modulus);
		map.put("exponent", exponent);
		return JsonResult.buildSuccess(map);
	}

	@SessionIgnore
	@SystemOperationLog(value = "登录", operationType = SystemOperationType.SELECT)
	@RequestMapping(value = "/doLogin")
	public JsonResult<Map<String, String>> doLogin() {
		String username = super.getParameter("username");
		String password = super.getParameter("password");
		String vercode = super.getParameter("vercode");
		if (StringUtils.isEmpty(username)) {
			return JsonResult.buildError("用户名不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			return JsonResult.buildError("密码不能为空");
		}
		if (StringUtils.isEmpty(vercode)) {
			return JsonResult.buildError("验证码不能为空");
		}
		if (!username.matches("^[A-Za-z0-9]+$")) {
			return JsonResult.buildError("用户名无效");
		}

		// 判断验证码是否输入正确
		Object rand = super.getSession().getAttribute("rand");
		String sessionCodeValue = null;
		if (rand != null) {
			sessionCodeValue = rand.toString();
		}
		if (StringUtils.isEmpty(sessionCodeValue) || !sessionCodeValue.equalsIgnoreCase(vercode.trim())) {
			return JsonResult.buildError("验证码输入错误");
		}

		String pwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + password);
		
		EntityWrapper<UserAdmin> entityWrapper = new EntityWrapper<UserAdmin>();
		UserAdmin userAdmin = new UserAdmin();
		userAdmin.setLoginName(username);
		entityWrapper.setEntity(userAdmin);
		UserAdmin selUserAdmin = userAdminService.selectOne(entityWrapper);
		if(selUserAdmin == null || !selUserAdmin.getLoginPwd().equals(pwdString)){
			return JsonResult.buildError("用户名或密不正确");
		}
		
//		AuthorityResult authorityResult = Authority.login(username, password);
//		if (!authorityResult.getSuccess()) {
//			return JsonResult.buildError(authorityResult.getMessage());
//		}
//		
//		Admin admin = JSONUtil.toBean(String.valueOf(authorityResult.getData()), Admin.class);
		
		super.getSession().setAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID, new AdminSession(Long.valueOf(selUserAdmin.getId()), selUserAdmin.getLoginName()));
		
		
		List<Menu> menus = WebMenus.getMenus();
		
		for (Menu menu : menus) {
			if (StringUtils.isEmpty(menu.getIcon())) {
				menu.setIcon(IconClass.getIcon());
			}
		}
		super.getSession().setAttribute("menus", menus);
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", ""));
		return JsonResult.buildSuccess("登录成功", map);
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	@SessionIgnore
	@RequestMapping(value = "/captcha", produces = MediaType.IMAGE_JPEG_VALUE)
	public BufferedImage captcha() {
		LineCaptcha captcha = CaptchaUtil.createLineCaptcha(300, 100, 4, 120);
		super.getSession().setAttribute("rand", captcha.getCode());// 将认证码存入SESSION
		return captcha.getImage();
	}
	
}
