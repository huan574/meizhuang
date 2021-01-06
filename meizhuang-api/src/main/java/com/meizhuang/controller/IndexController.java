package com.meizhuang.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.AdStation;
import com.meizhuang.entity.Complaint;
import com.meizhuang.entity.HtmlArticle;
import com.meizhuang.entity.LeaveWord;
import com.meizhuang.entity.OfferProduct;
import com.meizhuang.entity.OfferProductGroup;
import com.meizhuang.entity.Proclamation;
import com.meizhuang.entity.SearchHistory;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.result.JsonResult;
import com.meizhuang.service.AdStationService;
import com.meizhuang.service.ComplaintService;
import com.meizhuang.service.HtmlArticleService;
import com.meizhuang.service.LeaveWordService;
import com.meizhuang.service.OfferProductService;
import com.meizhuang.service.ProclamationService;
import com.meizhuang.service.SearchHistoryService;
import com.meizhuang.service.UserInfoService;
import com.meizhuang.utils.SystemParameterUtils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IndexController extends BaseController{

	@Autowired
	private OfferProductService offerProductService;
	
	@Autowired
	private LeaveWordService leaveWordService; 
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private SearchHistoryService searchHistoryService;
	
	@Autowired
	private ProclamationService proclamationService;
	
	@Autowired
	private AdStationService adStationService;
	
	
	@Autowired
	private HtmlArticleService htmlArticleService;
	
	
	@RequestMapping(value = "/")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		recommendGate(request, response);
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		recommendGate(request, response);
		return new ModelAndView("home");
	}
	
	private void recommendGate(HttpServletRequest request, HttpServletResponse response) {
		String recommendGateIds = SystemParameterUtils.get(SystemParameterEnum.RECOMMEND_GATE_IDS);
		List<UserInfo> list = new ArrayList<UserInfo>();
		if(!StringUtils.isBlank(recommendGateIds)){
			String[] gateIds = recommendGateIds.split("@");
			for(String id : gateIds){
				if(StringUtils.isNumeric(id)){
					UserInfo userInfo = userInfoService.selectById(Integer.valueOf(id));
					if(userInfo!= null){
						list.add(userInfo);
					}
				}
				
			}
		}
		if(list.size()>0){
			request.setAttribute("recommend_gate", list);
		}
		
		EntityWrapper<Proclamation> wrapper = new EntityWrapper<Proclamation>();
		wrapper.eq("state", 1);
		wrapper.orderAsc(Arrays.asList("order_num"));
		List<Proclamation> proclamations = proclamationService.selectList(wrapper);
		request.setAttribute("proclamations", proclamations);
		
		
		EntityWrapper<AdStation> adWrapper = new EntityWrapper<AdStation>();
		adWrapper.eq("state", 1);
		adWrapper.orderAsc(Arrays.asList("order_num"));
		List<AdStation> adStations = adStationService.selectList(adWrapper);
		request.setAttribute("adstations", adStations);
		if(adStations.size() >0){
			request.setAttribute("first_adstation", adStations.get(0));
			request.setAttribute("last_adstation", adStations.get(adStations.size() - 1));
		}
		//1跨境物流  2跑腿代发 3广告跳转 4 其它 5关于我们 
		//6商务合作 7服务条款 8法律声明 9秒换侠  10回收猿  11商家群
		EntityWrapper<HtmlArticle> htmlWrapper = new EntityWrapper<HtmlArticle>();
		htmlWrapper.in("type", 1, 2, 5 ,6 ,7 ,8, 9 ,10 ,11);
		List<HtmlArticle> htmlArticleList = htmlArticleService.selectList(htmlWrapper);
		
		for(HtmlArticle item : htmlArticleList){
			String url = "/h5page/"+item.getId();
			if(item.getType() == 1){//1跨境物流  
				request.setAttribute("wuliuUrl", url);
			}else if(item.getType() == 2){//2跑腿代发  
				request.setAttribute("daifaUrl", url);
			}else if(item.getType() == 5){//5关于我们 
				request.setAttribute("aboutUrl", url);
			}else if(item.getType() == 6){//6商务合作
				request.setAttribute("businessUrl", url);
			}else if(item.getType() == 7){//7服务条款
				request.setAttribute("serviceUrl", url);
			}else if(item.getType() == 8){//8法律声明
				request.setAttribute("lawUrl", url);
			}else if(item.getType() == 9){//9秒换侠
				request.setAttribute("miaohuanUrl", url);
			}else if(item.getType() == 10){//10回收猿
				request.setAttribute("recycleUrl", url);
			}else if(item.getType() == 11){//11商家群
				request.setAttribute("merchantUrl", url);
			}
		}
		

	}
	
	//搜索
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String kw = request.getParameter("kw");
		
		
		
		if(StringUtils.isBlank(kw)){
			request.setAttribute("msg", "请输入产品关键字");
			return new ModelAndView("search");
		}
		request.setAttribute("kw", kw);
		
		//BaseWrapper<OfferProduct> wrapper = new BaseWrapper<OfferProduct>(OfferProduct.selectFiled, OfferProduct.fromFiled);
		String [] kws = kw.split("\\s+");
		List<String> keys = new ArrayList<String>();
		for(String kItem :kws){
			keys.add(kItem);
			//wrapper.like("offer_product", kItem);
		}
		//wrapper.orderBy("mtime", false);
		
		//wrapper.last("limit 380");
		
		List<OfferProduct> listOfferProduct = offerProductService.searchByKey(keys);
		//List<OfferProduct> listOfferProduct = offerProductService.selectByList(wrapper);
		
		List<OfferProductGroup> groupList = new ArrayList<OfferProductGroup>();
		
		for(OfferProduct productItem :listOfferProduct){
			
			for(String key : kws){
				String replaceProduct = productItem.getOfferProduct().replace(key, "<font style='color:red'>"+key+"</font>");
				productItem.setOfferProduct(replaceProduct);
			}
			
			OfferProductGroup newGroup = new OfferProductGroup();
			newGroup.setUid(productItem.getUid());
			newGroup.setRandom(productItem.getRandom());
			newGroup.setMobile(productItem.getMobile());
			newGroup.setWeixin(productItem.getWeixin());
			newGroup.setWeixinImg(productItem.getWeixinImg());
			newGroup.setStoreName(productItem.getStoreName());
			newGroup.setStoreRemark(productItem.getStoreRemark());
			newGroup.setTradeStall(productItem.getTradeStall());

			SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
			String formatMtime = format.format(productItem.getMtime());
			newGroup.setDate(formatMtime);
			
			if(groupList.contains(newGroup)){
				int indexOf = groupList.indexOf(newGroup);
				OfferProductGroup getGroup = groupList.get(indexOf);
				List<OfferProduct> groupProducts = getGroup.getProductList();
				groupProducts.add(productItem);
			}else{
				List<OfferProduct>  groupProducts = new ArrayList<OfferProduct>();
				groupProducts.add(productItem);
				newGroup.setProductList(groupProducts);
				groupList.add(newGroup);
			}	
		}
		
		request.setAttribute("productGroupList", groupList);
		
		SearchHistory newHistory = new SearchHistory();
		newHistory.setKey(kw.trim());
		newHistory.setIp(getIpAddr(request));
		searchHistoryService.insert(newHistory);
		
		return new ModelAndView("search");
	}
	
	

	
	
	@RequestMapping(value = "/dangkou_goods")
	public ModelAndView dangkou_goods(HttpServletRequest request, HttpServletResponse response) {
		String gateId = request.getParameter("gate");
		String random = request.getParameter("random");
		
		EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<UserInfo>();
		entityWrapper.eq("uid", gateId);
		entityWrapper.eq("random", random);
		List<UserInfo> userList = userInfoService.selectList(entityWrapper);
		
		Date now = new Date();
		if(userList !=null && userList.size()>0){
			EntityWrapper<OfferProduct> offerWrapper = new EntityWrapper<OfferProduct>();
			//offerWrapper.eq("uid", gateId);
			//offerWrapper.orderBy("ctime", false);
			//offerWrapper.ge("ctime", new Date(now.getTime() - 10l * 24 * 60 * 60 * 1000));//最近10天报价
			//List<OfferProduct> offerProducts = offerProductService.selectList(offerWrapper);
			
			offerWrapper = new EntityWrapper<OfferProduct>();
			offerWrapper.eq("uid", gateId);
			offerWrapper.orderBy("ctime", false);
			offerWrapper.last("limit 0,10000");
			List<OfferProduct> offerProducts = offerProductService.selectList(offerWrapper);
			
			
			
			Map<String, List<OfferProduct>> mapProducts = new LinkedHashMap<String, List<OfferProduct>>();
			
			for(OfferProduct product : offerProducts){
				String fromDate = DateUtil.format(product.getCtime(), DatePattern.NORM_DATETIME_FORMAT);
				List<OfferProduct> maList = mapProducts.get(fromDate);
				if(maList == null){
					maList = new ArrayList<OfferProduct>();
					maList.add(product);
					mapProducts.put(fromDate, maList);
				}else{
					maList.add(product);
				}
			}
			
			//if(offerProducts.size()>0){
				request.setAttribute("mapProducts", mapProducts);
				//request.setAttribute("offerProducts", offerProducts);
				request.setAttribute("productCount", offerProducts.size());
				request.setAttribute("user", userList.get(0));
			//}
			
			
			
			
//			offerWrapper.orderDesc(Arrays.asList("mtime"));
//			
//			offerWrapper.last("limit 1");
//			
//			OfferProduct offerProduct = offerProductService.selectOne(offerWrapper);
//			if(offerProduct != null){
//				String fromDate = DateUtil.format(offerProduct.getMtime(), DatePattern.NORM_DATETIME_MINUTE_FORMAT);
//				request.setAttribute("nweProductTime", fromDate);
//			}
			
		}
		
		return new ModelAndView("dangkou_goods");
	}
	
	
	
	@RequestMapping(value = "/leaveWord")
	public JsonResult<String> leaveWord(HttpServletRequest request, HttpServletResponse response){
		Integer fromUidInt = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		String toUid = request.getParameter("uid");
		String leaveWord = request.getParameter("leave_word");
		String key = request.getParameter("key");
		
		if(StringUtils.isBlank(leaveWord)){
			return JsonResult.buildError("留言失败215");
		}
		
		LeaveWord newLeaveWord = new LeaveWord();
		newLeaveWord.setFormUid(fromUidInt);
		newLeaveWord.setToUid(Integer.valueOf(toUid));
		newLeaveWord.setLeaveWord(leaveWord);
		newLeaveWord.setKey(key);
		
		boolean result = leaveWordService.insert(newLeaveWord);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("留言失败");
		}
	}
	
	@RequestMapping(value = "/complaint")
	public JsonResult<String> complaint(HttpServletRequest request, HttpServletResponse response){
		Integer fromUidInt = (Integer)super.getSession().getAttribute(UserInfo.USER_SESSION_UID);
		String toUid = request.getParameter("uid");
		String complaint = request.getParameter("complaint");
		String key = request.getParameter("key");
		
		if(StringUtils.isBlank(complaint)){
			return JsonResult.buildError("投诉失败215");
		}
		
		Complaint newComplaint = new Complaint();
		newComplaint.setFormUid(fromUidInt);
		newComplaint.setToUid(Integer.valueOf(toUid));
		newComplaint.setComplaint(complaint);
		newComplaint.setKey(key);
		
		boolean result = complaintService.insert(newComplaint);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("投诉失败");
		}
	}
	
	//实时报价动态
	@RequestMapping(value = "/realTimeProduct")
	public ModelAndView realTimeProduct(HttpServletRequest request, HttpServletResponse response) {
		
		BaseWrapper<UserInfo> wrapper = new BaseWrapper<UserInfo>(UserInfo.selectFiled, UserInfo.fromFiled);
		
		wrapper.isNotNull("product_update_time")
		.groupBy("o.ctime").orderBy("product_update_time", false);
		
		wrapper.last("limit 200");
		
		List<UserInfo> list = userInfoService.selectByList(wrapper);
		
		for(UserInfo userInfo : list){
			
			String fromDate = DateUtil.format(userInfo.getProductUpdateTime(), DatePattern.NORM_DATETIME_PATTERN);
			userInfo.setSctime(fromDate);
			
		}
		
		request.setAttribute("list", list);
		
		return new ModelAndView("realTimeProduct");
	}
	

}
