package com.meizhuang.controller;




import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.OfferProduct;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.OfferProductService;
import com.meizhuang.service.UserInfoService;
import com.meizhuang.utils.SystemParameterUtils;
import com.meizhuang.utils.encrypt.MD5;
import com.meizhuang.web.Constants;

import net.coobird.thumbnailator.Thumbnails;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class UserInfoController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
			
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private OfferProductService offerProductService;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView register(){
		return new ModelAndView("register");
	}
	
	
	//用户个认信息
	@RequestMapping(value = "/personalInformation")
	public ModelAndView personal_information(HttpServletRequest request, HttpServletResponse response){
			Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
			
			
			if(userSession == null){
				request.setAttribute("msg", "请登录");
				return new ModelAndView("login");
			}else{
				UserInfo userInfo  = userInfoService.selectById(userSession);
				request.setAttribute("user", userInfo);
			}
			
			String opMsg = (String)super.getSession().getAttribute("op_msg");
			if(opMsg!=null){
				request.setAttribute("msg", opMsg);
				super.getSession().removeAttribute("op_msg");
			}
			
			
			return new ModelAndView("personal_information");
			
			
	}
	
	//用户首页
	@RequestMapping(value = "/userInfo")
	public ModelAndView user_info(HttpServletRequest request, HttpServletResponse response){
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		
		
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("login");
		}else{
			UserInfo userInfo  = userInfoService.selectById(userSession);
			request.setAttribute("user", userInfo);
		}
		
		String type = request.getParameter("type");
		
		String opMsg = (String)super.getSession().getAttribute("op_msg");
		if(opMsg!=null){
			request.setAttribute("msg", opMsg);
			super.getSession().removeAttribute("op_msg");
		}
		
//		if("2".equals(type)){
//			return new ModelAndView("personal_information");
//		}
		
		EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
		wrapper.eq("uid", userSession);
		int productInt = offerProductService.selectCount(wrapper);
		request.setAttribute("productCount", productInt);
		
		
		return new ModelAndView("user_info");
	}
	
	
	//提交注册
	@RequestMapping(value = "/registerDo")
	public ModelAndView registerDo(@RequestParam(value = "weixinImg", required = true) MultipartFile multipartFile,//微信二维码
			@RequestParam(value = "logoImg", required = true) MultipartFile multipartFile2,//logo

			HttpServletRequest request, HttpServletResponse response){
		
		String phone = request.getParameter("phone");//手机号
		
		String pwd = request.getParameter("pwd");//密码
		
		String pwd2 = request.getParameter("pwd2");
		
		String weixin = request.getParameter("weixin");//微信 账号
		
		String storeName = request.getParameter("storeName");//店名
		
		String tradeStall = request.getParameter("tradeStall");//市场档口号
		
		request.setAttribute("phone", phone);
		request.setAttribute("pwd", pwd);
		request.setAttribute("pwd2", pwd2);
		request.setAttribute("weixin", weixin);
		request.setAttribute("storeName", storeName);
		request.setAttribute("tradeStall", tradeStall);
		
		if(StringUtils.isEmpty(phone)){
			request.setAttribute("msg", "手机号不能为空");
			return new ModelAndView("register");
		}
		
		if(StringUtils.isEmpty(pwd)){
			request.setAttribute("msg", "密码不能为空");
			return new ModelAndView("register");
		}
		
		if(StringUtils.isEmpty(pwd2)){
			request.setAttribute("msg", "确认密码不能为空");
			return new ModelAndView("register");
		}
		
		if(!pwd.equals(pwd2)){
			request.setAttribute("msg", "密码和确认密码不一致");
			return new ModelAndView("register");
		}
		String newFileName = null;
		if(multipartFile != null && !multipartFile.isEmpty()){
			// 检查是否是图片
			try {
				BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
				if (bi == null) {
					request.setAttribute("msg", "请上传图片格式的文件");
					return new ModelAndView("register");
				}
			} catch (IOException e) {
				request.setAttribute("msg", "请上传图片格式的文件");
				return new ModelAndView("register");
			}
			
			String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
	    	
	    	if(StringUtils.isBlank(filePath)){
	    		request.setAttribute("msg", "未配置filePath");
				return new ModelAndView("register");
	    	}
	    	filePath = filePath +File.separator;
	    	 
	    	try{
	    		InputStream inputStream = multipartFile.getInputStream();
	    		String fileName = multipartFile.getOriginalFilename();
	            Path directory = Paths.get(filePath);
	            if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	            }
	            newFileName = UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
	            long copy = Files.copy(inputStream, directory.resolve(newFileName));
	            
	            Thumbnails.of(filePath+newFileName).scale(0.7f).toFile(filePath+newFileName);//按比例缩小

	            
	    	}catch(Exception e){
	    		request.setAttribute("msg", "图片上传失败");
				return new ModelAndView("register");
	    	}
			
		}
		
		
		String logonewFileName = null;
		if (multipartFile2 != null && !multipartFile2.isEmpty()) {
			// 检查是否是图片
			try {
				BufferedImage bi = ImageIO.read(multipartFile2.getInputStream());
				if (bi == null) {
					request.setAttribute("msg", "请上传图片格式的文件");
					return new ModelAndView("register");
				}
			} catch (IOException e) {
				request.setAttribute("msg", "请上传图片格式的文件");
				return new ModelAndView("register");
			}
			
			String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH) ;
	    	
	    	if(StringUtils.isBlank(filePath)){
	    		request.setAttribute("msg", "未配置filePath");
				return new ModelAndView("register");
	    	}
	    	filePath = filePath +File.separator+"logo"+File.separator;
	    	 
	    	try{
	    		InputStream inputStream = multipartFile2.getInputStream();
	    		String fileName = multipartFile2.getOriginalFilename();
	            Path directory = Paths.get(filePath);
	            if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	            }
	            logonewFileName = UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
	            long copy = Files.copy(inputStream, directory.resolve(logonewFileName));
	            Thumbnails.of(filePath+logonewFileName).scale(0.7f).toFile(filePath+logonewFileName);//按比例缩小

	    	}catch(Exception e){
	    		request.setAttribute("msg", "图片上传失败");
				return new ModelAndView("register");
	    	}
		}
		
		
		EntityWrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
		wrapper.eq("mobile", phone);
		
		int count = userInfoService.selectCount(wrapper);
		
		if(count > 0){
			request.setAttribute("msg", "手机号已注册");
			return new ModelAndView("register");
		}
		
		String pwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + pwd);

		UserInfo newUser = new UserInfo();
		newUser.setUserType(UserInfo.USER_KIND);
		newUser.setMobile(phone);
		newUser.setPwd(pwdString);
		newUser.setLoginStatus(1);
		newUser.setWeixin(weixin);
		newUser.setWeixinImg(newFileName);
		newUser.setLogoImg(logonewFileName);
		newUser.setTradeStall(tradeStall);
		newUser.setStoreName(storeName);
		newUser.setRandom(RandomStringUtils.randomNumeric(9));
		
		boolean result = userInfoService.insert(newUser);
		if(result){
			newUser = userInfoService.selectById(newUser.getUid());
			super.getSession().setAttribute(UserInfo.USER_SESSION_UID, newUser.getUid());
			request.setAttribute("user", newUser);
			return new ModelAndView("redirect:/userInfo");
		}
		return new ModelAndView("register");
	}
	
	
	@RequestMapping(value = "/loginDo")
	public ModelAndView loginDo(HttpServletRequest request, HttpServletResponse response){
		
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		
		EntityWrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
		wrapper.eq("mobile", phone);
		List<UserInfo> userList = userInfoService.selectList(wrapper);
		if(userList== null || userList.size() == 0){
			request.setAttribute("msg", "用户名或密码错误");
			return new ModelAndView("login");
		}
		UserInfo userInfo = userList.get(0);
		if(userInfo.getLoginStatus() == 0){
			request.setAttribute("msg", "账号禁止登录");
			return new ModelAndView("login");
		}
		
		String pwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + pwd);

		if(!pwdString.equals(userInfo.getPwd())){
			request.setAttribute("msg", "用户名或密码错误");
			return new ModelAndView("login");
		}
		
		
		
		super.getSession().setAttribute(UserInfo.USER_SESSION_UID, userInfo.getUid());
		request.setAttribute("user", userInfo);
		
		
		
		UserInfo upUserInfo = new UserInfo();
		upUserInfo.setUid(userInfo.getUid());
		upUserInfo.setLastLoginTime(new Date());
		upUserInfo.setLoginCookies(UUID.randomUUID().toString());
		userInfoService.updateById(upUserInfo);
		
		try{
			saveCookie(response, UserInfo.USER_COOKIES_NAME, upUserInfo.getLoginCookies(), 60 * 60 * 24 * 30);
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		
		
		

		return new ModelAndView("redirect:/userInfo");
		//return new ModelAndView("login");
	}
	
	//退出
	@RequestMapping(value = "/loginOut")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response){
		super.getSession().removeAttribute(UserInfo.USER_SESSION_UID);
		saveCookie(response, UserInfo.USER_COOKIES_NAME, null,0);
		super.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	
	//更新个人信息
	@RequestMapping(value = "/upUserInfo")
	public ModelAndView upUserInfo(@RequestParam(value = "weixinImg", required = true) MultipartFile multipartFile,//个人微信二维码
			@RequestParam(value = "logoImg", required = true) MultipartFile multipartFile2,//logo
			HttpServletRequest request, HttpServletResponse response){
		
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		
		String weixin = request.getParameter("weixin");
		String storeName = request.getParameter("storeName");
		String tradeStall = request.getParameter("tradeStall");
		UserInfo userInfo = null;
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("login");
		}else{
			userInfo  = userInfoService.selectById(userSession);
			request.setAttribute("user", userInfo);
		}
		
		if(userInfo == null){
			request.setAttribute("msg", "用户不存在");
			return new ModelAndView("login");
		}
		
		String newFileName = null;
		if (multipartFile != null && !multipartFile.isEmpty()) {
			// 检查是否是图片
			try {
				BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
				if (bi == null) {
					request.setAttribute("op_msg", "请上传图片格式的文件");
					return new ModelAndView("redirect:/personalInformation");
				}
			} catch (IOException e) {
				request.setAttribute("op_msg", "请上传图片格式的文件");
				return new ModelAndView("redirect:/personalInformation");
			}
			
			String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
	    	
	    	if(StringUtils.isBlank(filePath)){
	    		request.setAttribute("op_msg", "未配置filePath");
				return new ModelAndView("redirect:/personalInformation");
	    	}
	    	filePath = filePath+File.separator;
	    	 
	    	try{
	    		InputStream inputStream = multipartFile.getInputStream();
	    		String fileName = multipartFile.getOriginalFilename();
	            Path directory = Paths.get(filePath);
	            if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	            }
	            newFileName = UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
	            long copy = Files.copy(inputStream, directory.resolve(newFileName));
	            Thumbnails.of(filePath+newFileName).scale(0.7f).toFile(filePath+newFileName);//按比例缩小

	    	}catch(Exception e){
	    		request.setAttribute("op_msg", "图片上传失败");
				return new ModelAndView("redirect:/personalInformation");
	    	}
		}
		
		
		String logonewFileName = null;
		if (multipartFile2 != null && !multipartFile2.isEmpty()) {
			// 检查是否是图片
			try {
				BufferedImage bi = ImageIO.read(multipartFile2.getInputStream());
				if (bi == null) {
					request.setAttribute("op_msg", "请上传图片格式的文件");
					return new ModelAndView("redirect:/personalInformation");
				}
			} catch (IOException e) {
				request.setAttribute("op_msg", "请上传图片格式的文件");
				return new ModelAndView("redirect:/personalInformation");
			}
			
			String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
	    	
	    	if(StringUtils.isBlank(filePath)){
	    		request.setAttribute("op_msg", "未配置filePath");
				return new ModelAndView("redirect:/personalInformation");
	    	}
	    	filePath = filePath +File.separator+"logo"+File.separator;
	    	 
	    	try{
	    		InputStream inputStream = multipartFile2.getInputStream();
	    		String fileName = multipartFile2.getOriginalFilename();
	            Path directory = Paths.get(filePath);
	            if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	            }
	            logonewFileName = UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
	            long copy = Files.copy(inputStream, directory.resolve(logonewFileName));
	            Thumbnails.of(filePath+logonewFileName).scale(0.7f).toFile(filePath+logonewFileName);//按比例缩小

	    	}catch(Exception e){
	    		request.setAttribute("op_msg", "图片上传失败");
				return new ModelAndView("redirect:/personalInformation");
	    	}
		}
		
		
		UserInfo upUserInfo = new UserInfo();
		upUserInfo.setUid(userInfo.getUid());
		upUserInfo.setWeixin(weixin);
		upUserInfo.setStoreName(storeName);
		upUserInfo.setTradeStall(tradeStall);
		if(newFileName != null){
			upUserInfo.setWeixinImg(newFileName);
		}
		if(logonewFileName != null){
			upUserInfo.setLogoImg(logonewFileName);
		}
		
		userInfoService.updateById(upUserInfo);
		
		request.getSession().setAttribute("op_msg", "更新成功");
		
		return new ModelAndView("redirect:/personalInformation");
	}
	
 	//上传报价
	@RequestMapping(value = "/uploadProduct")
	public ModelAndView uploadProduct(HttpServletRequest request, HttpServletResponse response){
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("upload_product");
	}
	
	//报价管理
	@RequestMapping(value = "/productManage")
	public ModelAndView productManage(HttpServletRequest request, HttpServletResponse response){
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("product_manage");
	}
	
	@RequestMapping(value = "/productManageList")
	public PageResult<OfferProduct> productManageList(PageRequest pageRequest,HttpServletRequest request, HttpServletResponse response){
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		if(userSession == null){
			return PageResult.buildError("请登录");
		}
		
		Page<OfferProduct> page = new Page<OfferProduct>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());

		String sctime = request.getParameter("sctime");
		
		EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
		wrapper.eq("uid", userSession);
		if(StringUtils.isNotEmpty(sctime)) {
			String[] ctime = sctime.split("~");
			if (ctime.length == 2) {
				wrapper.ge("ctime", ctime[0]);
				wrapper.le("ctime", ctime[1]);
			}
		}
		
		// 查询数据返回总记录数和数据
		offerProductService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	
	
	//上传报价
	@RequestMapping(value = "/uploadProductDo")
	public ModelAndView uploadProductDo(HttpServletRequest request, HttpServletResponse response){
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		UserInfo userInfo = null;
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("redirect:/login");
		}else{
			userInfo  = userInfoService.selectById(userSession);
			request.setAttribute("user", userInfo);
		}
		
		if(userInfo == null){
			request.setAttribute("msg", "数据错误");
			return new ModelAndView("redirect:/login");
		}
		
		if(userInfo.getAuditStatus() == 0){
			request.setAttribute("msg", "你还未审核通过，请联系管理员");
			return new ModelAndView("upload_product");
		}
		
		
		
		String productsStr = request.getParameter("products");
		if(StringUtils.isBlank(productsStr)){
			request.setAttribute("msg", "上传的产品为空");
			return new ModelAndView("upload_product");
		}
		
		String key = "lock_upload_product_"+userSession;
		
		if(super.getSession().getAttribute(key)!= null){
			request.setAttribute("msg", "提交过快");
			return new ModelAndView("upload_product");
		}
		
		try{
			
			super.getSession().setAttribute(key, key);
			productsStr = productsStr.replaceAll("<br/>", "\n").replaceAll("<br>", "\n");
			
			String products[] = productsStr.split("\\r\\n|\\n|\\r");
	
			List<OfferProduct> addList = new ArrayList<OfferProduct>();
			
			Date nowDate = new Date();
			
			for(String item: products){
				
				String offerProduct = item.trim();
				
				if(StringUtils.isBlank(offerProduct)){
					continue;
				}
				
				String priceStr =  null;
				List<String> numList = getFullNumFromString(offerProduct);
				if(numList.size()>0){
					priceStr = numList.get(numList.size()-1);
				}
				
				
				
				
	//			EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
	//			OfferProduct wProduct = new OfferProduct();
	//			wProduct.setUid(userSession);
	//			wProduct.setOfferProduct(offerProduct);
	//			
	//			
	//			wrapper.setEntity(wProduct);
	//			List<OfferProduct> list2 = offerProductService.selectList(wrapper);
	//			
	//			if(list2!=null && list2.size() > 0){
	//				OfferProduct itemProduct = list2.get(0);
	//				wProduct.setId(itemProduct.getId());
	//				wProduct.setMtime(new Date());
	//				offerProductService.updateById(wProduct);
	//				
	//			}else{
	//				OfferProduct newProduct = new OfferProduct();
	//				newProduct.setUid(userSession);
	//				newProduct.setOfferProduct(item.trim());
	//				if(priceStr!=null){
	//					newProduct.setGatherPrice(new BigDecimal(priceStr));
	//				}
	//				addList.add(newProduct);
	//			}
				
				OfferProduct newProduct = new OfferProduct();
				newProduct.setUid(userSession);
				if(priceStr !=null){
					newProduct.setProduct(item.trim().replace(priceStr, ""));
				}else{
					newProduct.setProduct(item.trim());
				}
				
				newProduct.setOfferProduct(item.trim());
				newProduct.setCtime(nowDate);
				
				if(priceStr!=null){
					newProduct.setGatherPrice(new BigDecimal(priceStr));
				}
				addList.add(newProduct);
				
				
			}
		
			if(addList.size()>0){
				offerProductService.insertBatch(addList);
				UserInfo upUserInfo = new UserInfo();
				upUserInfo.setUid(userSession);
				upUserInfo.setProductUpdateTime(nowDate);
				userInfoService.updateById(upUserInfo);
			}
		}finally{
			super.getSession().removeAttribute(key);
		}
		
		
		
		request.setAttribute("msg", "产品报价上传成功");
		
		return new ModelAndView("upload_product");
	}
	
	
	
	
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	public JsonResult<String> batchDel(@RequestParam(value = "ids", required = true) String ids){
		
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		if(userSession == null){
			return JsonResult.buildError("请登录");
		}
		
		EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
		OfferProduct newO = new OfferProduct();
		
		
		String[] idms = ids.split(",");
		
		for(String id : idms){
			
			newO.setUid(userSession);
			newO.setId(Integer.valueOf(id));
			wrapper.setEntity(newO);
			offerProductService.delete(wrapper);
		}
		
		return JsonResult.buildSuccess();
	}
	
	//删除商品报价
	@RequestMapping(value = "/delAllProduct")
	public JsonResult<String> delAllProduct(HttpServletRequest request, HttpServletResponse response) {
		Integer userSession = (Integer) super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		if (userSession == null) {
			return JsonResult.buildError("请登录");
		}
		
		EntityWrapper<OfferProduct> wrapper = new EntityWrapper<OfferProduct>();
		OfferProduct wProduct = new OfferProduct();
		wProduct.setUid(userSession);
		wrapper.setEntity(wProduct);
		offerProductService.delete(wrapper);
		
		return JsonResult.buildSuccess("删除成功");
		
	}
	
	@RequestMapping(value = "/upPwd")
	public ModelAndView upPwd(HttpServletRequest request, HttpServletResponse response) {
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("up_pwd");
	}
	
	@RequestMapping(value = "/upPwdDo")
	public ModelAndView upPwdDo(HttpServletRequest request, HttpServletResponse response) {
		Integer userSession = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		UserInfo userInfo = null;
		if(userSession == null){
			request.setAttribute("msg", "请登录");
			return new ModelAndView("redirect:/login");
		}else{
			userInfo  = userInfoService.selectById(userSession);
			request.setAttribute("user", userInfo);
		}
		
		if(userInfo == null){
			request.setAttribute("msg", "数据错误");
			return new ModelAndView("redirect:/login");
		}
		
		String oldPwd = request.getParameter("oldPwd");//密码
		
		String newPwd = request.getParameter("newPwd");
		
		String newPwd2 = request.getParameter("newPwd2");//
		
		if(StringUtils.isBlank(oldPwd)){
			request.setAttribute("msg", "原密码不能为空");
			return new ModelAndView("up_pwd");
		}
		
		if(StringUtils.isBlank(newPwd)){
			request.setAttribute("msg", "新密码不能为空");
			return new ModelAndView("up_pwd");
		}
		
		if(!newPwd.equals(newPwd2)){
			request.setAttribute("msg", "新密码和确认新密码不一致");
			return new ModelAndView("up_pwd");
		}
		
		String oldPwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + oldPwd);
		
		String newPwdString = MD5.MD5EncodeUTF8(Constants.PWD_MD5_KEY + newPwd2);
		
		if(!oldPwdString.equals(userInfo.getPwd())){
			request.setAttribute("msg", "原密码不正确");
			return new ModelAndView("up_pwd");
		}
		
		UserInfo newUser = new UserInfo();
		newUser.setUid(userInfo.getUid());
		newUser.setPwd(newPwdString);
		userInfoService.updateById(newUser);
		request.getSession().setAttribute("op_msg", "密码修改成功");
		return new ModelAndView("redirect:/userInfo");
	}
	
}



