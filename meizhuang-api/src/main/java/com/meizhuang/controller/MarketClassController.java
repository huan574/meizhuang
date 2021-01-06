package com.meizhuang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.MarketClass;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.service.MarketClassService;
import com.meizhuang.service.UserInfoService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class MarketClassController extends BaseController{

	@Autowired
	private MarketClassService marketClassService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/marketClass")
	public ModelAndView marketClass(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		Integer cid = null;
		Integer mid = null;
		Integer lid = null;
		if(StringUtils.isNotBlank(id)){
			
			EntityWrapper<MarketClass> wrapper = new EntityWrapper<MarketClass>();
			wrapper.eq("id", id);
			
			MarketClass marketClass = marketClassService.selectOne(wrapper);
			List<MarketClass> marketClassList = null;
			if(marketClass != null){
				
				if(marketClass.getType() == 1){
					cid = marketClass.getId();
					marketClassList = getSMarketClass(cid);
					if(marketClassList.size() > 0){
						mid = marketClassList.get(0).getId();
					}
					
				}else if(marketClass.getType() == 2){
					mid= marketClass.getId();
					marketClass = getPMarketClass(marketClass.getpId());
					if(marketClass != null){
						cid = marketClass.getId();
					}
					//marketClassList = getSMarketClass(mid);
					//if(marketClassList.size() > 0){
					//	lid = marketClassList.get(0).getId();
					//}
					
				}else if(marketClass.getType() == 3){
					lid = marketClass.getId();
					marketClass = getPMarketClass(marketClass.getpId());
					
					if(marketClass != null){
						mid = marketClass.getId();
						
						marketClass = getPMarketClass(marketClass.getpId());
						
						if(marketClass!= null){
							cid = marketClass.getId();
						}
					}
				}
				
			}
			
		}else{
			EntityWrapper<MarketClass> wrapper = new EntityWrapper<MarketClass>();
			wrapper.eq("type", 1);
			List<MarketClass> marketClassList = marketClassService.selectList(wrapper);
			if(marketClassList.size() > 0){
				cid = marketClassList.get(0).getId();
			}
			marketClassList = getSMarketClass(cid);
			if(marketClassList.size() > 0){
				mid = marketClassList.get(0).getId();
			}

		}
		
		EntityWrapper<MarketClass> cityWrapper = new EntityWrapper<MarketClass>();
		cityWrapper.eq("type", "1");
		List<MarketClass> list = marketClassService.selectList(cityWrapper);
		request.setAttribute("cityClass", list);
		if(cid != null){
			request.setAttribute("cid", cid);
		}
		if(mid != null){
			request.setAttribute("mid", mid);
		}
		if(lid != null){
			request.setAttribute("lid", lid);
		}
		
		if(mid != null){
			EntityWrapper<MarketClass> wrapper = new EntityWrapper<MarketClass>();
			wrapper.eq("p_id", cid);
			List<MarketClass> mlist = marketClassService.selectList(wrapper);
			request.setAttribute("mClass", mlist);
			
			wrapper = new EntityWrapper<MarketClass>();
			wrapper.eq("p_id", mid);
			List<MarketClass> llist = marketClassService.selectList(wrapper);
			request.setAttribute("lClass", llist);
			
			if(lid != null){
				
				for(MarketClass litem: llist){
					
					if(lid == litem.getId().intValue()){
						for(MarketClass mitem : mlist){
							if(litem.getpId() == mitem.getId().intValue()){
								mitem.setClassName(mitem.getClassName()+"/"+litem.getClassName());
								break;
							}
						}
						break;
					}
					
				}
				
				
			}
			
		}
		
		if(lid != null){
			List<UserInfo>  userInfoList = getUserInfoByClass(lid);
			request.setAttribute("userInfoList", userInfoList);
		}
		
		
		return new ModelAndView("market_class");
	}
	
	
	private List<UserInfo> getUserInfoByClass(Integer lid){
		//EntityWrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
		BaseWrapper<UserInfo> baseWrapper = new BaseWrapper<UserInfo>(UserInfo.selectFiled3, UserInfo.fromFiled3);
		//baseWrapper.eq("u.market_lid", lid);
		//baseWrapper.eq("u.audit_status", 1);
		baseWrapper.eq("m4.p_id", lid);
		
		
		List<UserInfo> list1 = new ArrayList<UserInfo>();
		
		List<UserInfo> list = userInfoService.selectByList(baseWrapper);
		for(UserInfo userInfo : list){
			if(userInfo.getAuditStatus() == null || userInfo.getAuditStatus() == 1){
				list1.add(userInfo);
			}
		}
		
		
		return list1;
	}
	
	
	private List<MarketClass> getSMarketClass(Integer id){
		EntityWrapper<MarketClass> wrapper = new EntityWrapper<MarketClass>();
		wrapper.eq("p_id", id);
		List<MarketClass> marketClassList = marketClassService.selectList(wrapper);
		return marketClassList;
	}
	
	private MarketClass getPMarketClass(Integer pId){
		EntityWrapper<MarketClass> wrapper = new EntityWrapper<MarketClass>();
		wrapper.eq("id", pId);
		MarketClass marketClass = marketClassService.selectOne(wrapper);
		return marketClass;
	}
	
}
