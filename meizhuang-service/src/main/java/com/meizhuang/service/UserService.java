package com.meizhuang.service;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.entity.Token;

import com.meizhuang.entity.User;

import com.meizhuang.entity.enums.OperationTypeEnum;

import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.mapper.meizhuang.UserMapper;
import com.meizhuang.result.JsonResult;

import com.meizhuang.utils.UserOperationUtils;
import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.web.Constants;

@Service
public class UserService extends BaseService<UserMapper, User> {

	@Autowired
	private TokenService tokenService;
	
	
	/***
	 * 登录
	 * 
	 * @throws Exception
	 */
	@Transactional
	public JsonResult<User> login(User user, Integer loginType) {
		EntityWrapper<User> entityWrapper = null;
		if (loginType == 0) {// 手机号登录
			String countryCode = "+86";
			if (StringUtils.isNotBlank(user.getCountryCode())) {
				countryCode = user.getCountryCode();
			}
			// 判断用户是否存在
			entityWrapper = new EntityWrapper<User>(new User.Builder()//
					.countryCode(countryCode)//
					.mobileNumber(user.getMobileNumber())//
					.appId(user.getAppId())//
					.build());
		} else {// 账号登录
				// 判断用户是否存在
			entityWrapper = new EntityWrapper<User>(new User.Builder()//
					.uid(user.getUid())//
					.appId(user.getAppId())//
					.build());
		}
		User loginUser = super.selectOne(entityWrapper);
		if (loginUser == null) {
			return JsonResult.buildError(BizExceptionEnum.USER_NOT_EXISTED);
		}
		if ("0".equals(loginUser.getLoginStatus().toString())) {
			return JsonResult.buildError("该用户未激活，请联系管理员激活");
		}
		if ("2".equals(loginUser.getLoginStatus().toString())) {
			return JsonResult.buildError("该用户已被禁止登录，禁用原因：" + loginUser.getLoginRemark() + "，具体原因请联系管理员");
		}
		if (loginType == 1) {
			// 判断密码是否正确
			String password = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + loginUser.getUid().toString() + user.getLoginPword());
			if (!password.equals(loginUser.getLoginPword())) {
				return JsonResult.buildError("账号或密码错误");
			}
		}
		// 登录成功，更新最后一次登录时间
		loginUser.setLastLoginTime(super.baseMapper.getNowTime());
		loginUser.setImei(user.getImei());
		loginUser.setPhoneSystemModel(user.getPhoneSystemModel());
		loginUser.setDeviceBrand(user.getDeviceBrand());
		super.updateById(loginUser);
		UserOperationUtils.addLog(loginUser.getUid(), OperationTypeEnum.LOGIN); // 添加日志
		return JsonResult.buildSuccess(loginUser);
	}

	
	public JsonResult<User> selectByUId(Integer uid) {
		User user = this.selectById(uid);
		if (user == null) {
			return JsonResult.buildError(BizExceptionEnum.USER_NOT_EXISTED);
		}
		return JsonResult.buildSuccess(user);
	}
	
	@Transactional
	public JsonResult<String> logout(User user) throws Exception {
		JsonResult<User> userResult = this.selectByUId(user.getUid());
		if (!userResult.isSuccess()) {
			return JsonResult.buildError(userResult);
		}
		User loginUser = userResult.getData();

		EntityWrapper<Token> wrapper = new EntityWrapper<Token>(new Token.Builder().uid(loginUser.getUid()).build());
		// 移除token表中的数据
		tokenService.delete(wrapper);
		UserOperationUtils.addLog(loginUser.getUid(), OperationTypeEnum.LOGOUT); // 添加日志
		return JsonResult.buildSuccess("退出登录成功");
	}

}