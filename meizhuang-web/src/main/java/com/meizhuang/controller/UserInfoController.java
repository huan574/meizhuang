package com.meizhuang.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.MarketClass;
import com.meizhuang.entity.OfferProduct;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.MarketClassService;
import com.meizhuang.service.OfferProductService;
import com.meizhuang.service.UserInfoService;
import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.web.Constants;

import io.swagger.annotations.Api;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping(value = "/flow/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private OfferProductService offerProductService;
	
	@Autowired
	private MarketClassService marketClassService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("user/userList");
	}
	
	
	
	@RequestMapping(value = "/geUserInfos", method = RequestMethod.POST)
	public PageResult<UserInfo> getUserInfos(PageRequest pageRequest, UserInfo requestBody) {
		Page<UserInfo> page = new Page<UserInfo>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<UserInfo> wrapper = buildWrapper(requestBody);
		
		// 查询数据返回总记录数和数据
		userInfoService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	private BaseWrapper<UserInfo> buildWrapper(UserInfo requestBody){
		//EntityWrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
		BaseWrapper<UserInfo> wrapper = new BaseWrapper<UserInfo>(UserInfo.selectFiled2, UserInfo.fromFiled2);
		if(StringUtils.isNotEmpty(requestBody.getMobile())){
			wrapper.like("u.mobile", requestBody.getMobile());
		}
		if(StringUtils.isNotEmpty(requestBody.getSctime())) {
			String[] ctime = requestBody.getSctime().split("~");
			if (ctime.length == 2) {
				wrapper.ge("u.ctime", ctime[0]);
				wrapper.le("u.ctime", ctime[1]);
			}
		}
		return wrapper;
	}
	
	
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public JsonResult<String> updateStatus(UserInfo requestBody) {
		UserInfo userInfo = userInfoService.selectById(requestBody.getUid());
		if(userInfo == null){
			return JsonResult.buildError("用户不存在");
		}
		
		UserInfo update = new UserInfo();
		update.setUid(requestBody.getUid());
		if(requestBody.getAuditStatus() != null){
			update.setAuditStatus(requestBody.getAuditStatus());
		}
		update.setAuditRemark(requestBody.getAuditRemark());

		if(requestBody.getLoginStatus() != null){
			update.setLoginStatus(requestBody.getLoginStatus());
		}
		
		userInfoService.updateById(update);
		
		return JsonResult.buildSuccess();
		
	}
	
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public JsonResult<String> updateUserInfo(UserInfo requestBody) {
		UserInfo userInfo = userInfoService.selectById(requestBody.getUid());
		if(userInfo == null){
			return JsonResult.buildError("用户不存在");
		}
		
		UserInfo update = new UserInfo();
		update.setUid(requestBody.getUid());
		update.setMobile(requestBody.getMobile());
		update.setWeixin(requestBody.getWeixin());
		update.setStoreName(requestBody.getStoreName());
		update.setStoreRemark(requestBody.getStoreRemark());
		update.setTradeStall(requestBody.getTradeStall());
		
		userInfoService.updateById(update);
		
		return JsonResult.buildSuccess();
		
	}
	
	
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	public JsonResult<String> resetPwd(UserInfo requestBody) {
		UserInfo userInfo = userInfoService.selectById(requestBody.getUid());
		if(userInfo == null){
			return JsonResult.buildError("用户不存在");
		}
		
		String pwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + requestBody.getPwd());

		UserInfo update = new UserInfo();
		update.setUid(requestBody.getUid());
		update.setPwd(pwdString);
		
		userInfoService.updateById(update);
		
		return JsonResult.buildSuccess();
		
	}
	
	
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	public JsonResult<String> batchDel(@RequestParam(value = "ids", required = true) String ids){
		
		String[] idms = ids.split(",");
		
		for(String id : idms){
			
			Integer uidInteger = Integer.valueOf(id);
			userInfoService.deleteById(uidInteger);
			
			EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
			wrapper.eq("uid", uidInteger);
			
			offerProductService.delete(wrapper);
		}
		
		return JsonResult.buildSuccess();
	}
	
	
	@RequestMapping(value = "/marketClass", method = RequestMethod.POST)
	public JsonResult<String> marketClass(UserInfo requestBody) {
		UserInfo userInfo = userInfoService.selectById(requestBody.getUid());
		if(userInfo == null){
			return JsonResult.buildError("用户不存在");
		}
		
		Integer marketLid = requestBody.getMarketLid();
		if(marketLid == null){
			return JsonResult.buildError("请选择位置");
		}
		MarketClass marketClass = marketClassService.selectById(marketLid);
		
		if(marketClass == null){
			return JsonResult.buildError("位置信息不存在");
		}
		
		if(marketClass.getType() != 3){
			return JsonResult.buildError("位置类型错误");
		}
		
		UserInfo update = new UserInfo();
		update.setUid(requestBody.getUid());
		update.setMarketLid(marketClass.getId());
		update.setMarketSid(requestBody.getMarketSid());
		
		userInfoService.updateById(update);
		
		return JsonResult.buildSuccess();
		
	}
	
	
	
	 
}
