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
import com.meizhuang.entity.OfferProduct;
import com.meizhuang.entity.UserInfo;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.OfferProductRequest;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.OfferProductService;
import com.meizhuang.service.UserInfoService;

import io.swagger.annotations.Api;

@Api(tags = "相关接口")
@RestController
@RequestMapping(value = "/flow/offerProduct")
public class OfferProductController extends BaseController {

	@Autowired
	private OfferProductService offerProductService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("user/offerProductList");
	}
	
	@RequestMapping(value = "/getOfferProducts", method = RequestMethod.POST)
	public PageResult<OfferProduct> getOfferProducts(PageRequest pageRequest, OfferProductRequest requestBody) {
		Page<OfferProduct> page = new Page<OfferProduct>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<OfferProduct> wrapper = buildWrapper(requestBody);
		
		// 查询数据返回总记录数和数据
		offerProductService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	private BaseWrapper<OfferProduct> buildWrapper(OfferProductRequest requestBody){
		BaseWrapper<OfferProduct> wrapper = new BaseWrapper<OfferProduct>(OfferProduct.selectFiled, OfferProduct.fromFiled);
		
		if(StringUtils.isNotEmpty(requestBody.getMobile())){
			wrapper.eq("u.mobile", requestBody.getMobile());
		}
		if(StringUtils.isNotEmpty(requestBody.getTradeStall())){
			wrapper.like("u.trade_stall", requestBody.getTradeStall());
		}
		if(StringUtils.isNotEmpty(requestBody.getSctime())) {
			String[] ctime = requestBody.getSctime().split("~");
			if (ctime.length == 2) {
				wrapper.ge("o.ctime", ctime[0]);
				wrapper.le("o.ctime", ctime[1]);
			}
		}
		
		
		return wrapper;
	}
	
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	public JsonResult<String> batchDel(@RequestParam(value = "ids", required = true) String ids){
		
		String[] idms = ids.split(",");
		
		for(String id : idms){
			offerProductService.deleteById(Integer.valueOf(id));
		}
		
		return JsonResult.buildSuccess();
	}
	
	
}
