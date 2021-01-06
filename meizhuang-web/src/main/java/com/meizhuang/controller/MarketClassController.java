package com.meizhuang.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.MarketClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "市场分类接口")
@RestController
@RequestMapping(value = "/flow/marketClass")
public class MarketClassController extends BaseController {

	@Autowired
	private MarketClassService marketClassService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("market/marketClassList");
	}
	
	@ApiOperation(value = "分页查询信息", notes = "")
	@RequestMapping(value = "/getMarketClassList", method = RequestMethod.POST)
	public PageResult<MarketClass> getMarketClassList(MarketClass request, PageRequest pageRequest) {
		
		BaseWrapper<MarketClass> baseWrapper = new BaseWrapper<MarketClass>(MarketClass.selectFiled, MarketClass.fromFiled);
		if(!StringUtils.isBlank(request.getClassName())){
			baseWrapper.like("m1.class_name", request.getClassName());
		}
		if(request.getType() != null){
			baseWrapper.eq("m1.type", request.getType());
		}
		
		Page<MarketClass> page = new Page<MarketClass>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		marketClassService.selectPage(page,baseWrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	@ApiOperation(value = "增加", notes = "")
	@RequestMapping(value = "/addMarketClass", method = RequestMethod.POST)
	public JsonResult<String> addMarketClass(
			MarketClass requestBody){

		if((requestBody.getType()== null || requestBody.getType() == 0)){
			return JsonResult.buildError("请选择具体类型");
		}
		boolean result = marketClassService.insert(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("增加失败");
		}
	}
	
	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/delMarketClass", method = RequestMethod.POST)
	public JsonResult<String> delMarketClass(@RequestParam(value = "ids", required = true) String ids){
		
		String[] idSp = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		
		for(String idStr : idSp){
			Integer id = Integer.valueOf(idStr);
			if(id<0){
				continue;
			}
			idList.add(id);
			
			MarketClass clazz = marketClassService.selectById(id);
			if(clazz == null){
				return JsonResult.buildError("数据有误");
			}
			
			EntityWrapper<MarketClass> entityWrapper = new EntityWrapper<MarketClass>();
			entityWrapper.eq("p_id", id);
			
			int count = marketClassService.selectCount(entityWrapper);
			
			if(count > 0){
				return JsonResult.buildError("请先删除关联的子分类");
			}
		}
		
		
		
		boolean result =marketClassService.deleteBatchIds(idList);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("删除失败");
		}
	}
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateMarketClass", method = RequestMethod.POST)
	public JsonResult<String> updateMarketClass(MarketClass requestBody){
		
		MarketClass upMarketClass = new MarketClass();
		upMarketClass.setId(requestBody.getId());
		upMarketClass.setClassName(requestBody.getClassName());
		
		boolean result = marketClassService.updateById(upMarketClass);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("更新失败");
		}
	}
	
	@ApiOperation(value = "查询市场", notes = "")
	@RequestMapping(value = "/getParentMarketClass", method = RequestMethod.POST)
	public JsonResult<List<MarketClass>> getParentTypeMarketClass(@RequestParam(value = "type", required = true) Integer type){
		
		BaseWrapper<MarketClass> entityWrapper = new BaseWrapper<MarketClass>(MarketClass.selectFiled, MarketClass.fromFiled);
		
		entityWrapper.eq("m1.type", type);
		
		List<MarketClass> list = marketClassService.selectByList(entityWrapper);
		
		return JsonResult.buildSuccess(list);
		
	}
	
	@ApiOperation(value = "查询市场", notes = "")
	@RequestMapping(value = "/getParentIdMarketClass", method = RequestMethod.POST)
	public JsonResult<List<MarketClass>> getParentIdMarketClass(@RequestParam(value = "pid", required = true) Integer pid){
		
		EntityWrapper<MarketClass> entityWrapper = new EntityWrapper<MarketClass>();
		
		entityWrapper.eq("p_id", pid);
		
		List<MarketClass> list = marketClassService.selectList(entityWrapper);
		
		return JsonResult.buildSuccess(list);
		
	}
	
	
}
