package com.meizhuang.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.entity.Token;
import com.meizhuang.entity.User;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.mapper.meizhuang.TokenMapper;
import com.meizhuang.result.JsonResult;

import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.web.Constants;

@Service
public class TokenService extends BaseService<TokenMapper, Token> {

	private static Logger logger = Logger.getLogger(TokenService.class);
	
	@Autowired
	private UserService userService;

	@Transactional
	public JsonResult<Token> getToken(User user) {
		String tokenStr = MD5.MD5EncodeUTF8(Constants.TOEKN_MD5_KEY + user.getUid().toString() + System.currentTimeMillis() + Math.random());
		Token token=this.baseMapper.queryLoginMinuteTime(user.getUid() );
		if(token==null) {
			token = new Token.Builder().token(tokenStr).uid(user.getUid()).build();
			super.insert(token);
		}else {
			int loginMinuteTime=token.getLoginMinuteTime();
			if(user.getUserType().intValue()!=0 || (user.getUserType().intValue()==0  && loginMinuteTime>120)) { //代理商的token 登录120分钟更换一次。其它每次登录更换
				// 先移除，再添加
				EntityWrapper<Token> wrapper = new EntityWrapper<Token>(new Token.Builder().uid(user.getUid()).build());
				super.delete(wrapper);
				token = new Token.Builder().token(tokenStr).uid(user.getUid()).build();
				super.insert(token);
				logger.info("uid:"+user.getUid() +" type:"+user.getUserType());
			}
		}
		token = super.selectById(token.getId());
		logger.info("uid:"+user.getUid() +" "+user);
		return JsonResult.buildSuccess(token);
	}

	
	public User getUser(String token) {
		EntityWrapper<Token> entityWrapper = new EntityWrapper<Token>(new Token.Builder().token(token).build());
		Token tokenData = super.selectOne(entityWrapper);
		if (tokenData == null) {
			return null;
		}
		User user = userService.selectById(tokenData.getUid());
		return user;
	}

	
	public boolean checkToken(String token) {
		EntityWrapper<Token> entityWrapper = new EntityWrapper<Token>(new Token.Builder().token(token).build());
		if (super.selectCount(entityWrapper) == 0) {
			return false;
		}
		return true;
	}

	
	public JsonResult<Object> validatorToken(Integer uid, Integer appId, Integer userType, String token,String phoneSystemModel,String imei, String deviceBrand,String deviceName) {
		EntityWrapper<User> userWrapper = new EntityWrapper<User>(new User.Builder().uid(uid).appId(appId).userType(userType.byteValue()).build());
		User user = userService.selectOne(userWrapper);
		if (user == null) {
			return JsonResult.buildError(BizExceptionEnum.USER_NOT_EXISTED);
		}
		if (user.getLoginStatus().intValue() != 1) { // 登录状态不为正常状态
			return JsonResult.buildError("您已被禁止登录，禁用原因：" + user.getLoginRemark());
		}
		
		//只有用户才判断
		if(userType==1) {
			//换设备
			if(user.getPhoneSystemModel()!=null) {
				if(!user.getPhoneSystemModel().equals(phoneSystemModel)) {
					return JsonResult.buildError(BizExceptionEnum.CHANGE_MOBILE);
				}
			}
			
			if(user.getImei()!=null) {
				if(!user.getImei().equals(imei)) {
					return JsonResult.buildError(BizExceptionEnum.CHANGE_MOBILE);
				}
			}
			
			if(user.getDeviceName()!=null){
				if(!user.getDeviceName().equals(deviceName)) {
					return JsonResult.buildError(BizExceptionEnum.CHANGE_MOBILE);
				}
			}
			
		}
		EntityWrapper<Token> tokenWrapper = new EntityWrapper<Token>(new Token.Builder().uid(uid).token(token).build());
		if (super.selectCount(tokenWrapper) == 0) {
			return JsonResult.buildError(BizExceptionEnum.SESSION_TIMEOUT);
		}
		
		//如果是代理 token  更换一次有效时间360分钟  本方法是验证token的有效性）
		if(userType==0) {
			Token token1=this.baseMapper.queryLoginMinuteTime(user.getUid() );	
			int loginMinuteTime=token1.getLoginMinuteTime();
			if(loginMinuteTime>360) {
				return JsonResult.buildError(BizExceptionEnum.SESSION_TIMEOUT);
			}
		}
		
		return JsonResult.buildSuccess();
	}

}