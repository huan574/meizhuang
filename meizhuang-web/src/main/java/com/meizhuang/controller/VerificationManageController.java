package com.meizhuang.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.VerificationManage;
import com.meizhuang.entity.Version;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.param.request.VersionAddRequest;
import com.meizhuang.param.request.VersionRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.VerificationManageService;
import com.meizhuang.service.VersionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/flow/verificationmanage")
public class VerificationManageController extends BaseController{

	@Autowired
	private VerificationManageService verificationManageService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("system/verificationmanage");
	}
	
	@RequestMapping(value = "/getVerificationManageList", method = RequestMethod.POST)
	public PageResult<VerificationManage> getVerificationManageList(PageRequest pageRequest, VerificationManage requestBody) {
		Page<VerificationManage> page = new Page<VerificationManage>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		BaseWrapper<VerificationManage> wrapper = buildWrapper(requestBody);
		// 查询数据返回总记录数和数据
		verificationManageService.selectPage(page, wrapper);
		
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	private BaseWrapper<VerificationManage> buildWrapper(VerificationManage requestBody) {
		BaseWrapper<VerificationManage> entityWrapper = new BaseWrapper<VerificationManage>(VerificationManage.selectFiled,VerificationManage.fromFiled);
		if (requestBody.getAppId()!=null) {
			entityWrapper.eq("t.app_id", requestBody.getAppId());
		}
		if (requestBody.getVerificationType() != null) {
			entityWrapper.eq("t.verification_type", requestBody.getVerificationType());
		}
		if (requestBody.getState()!=null) {
			entityWrapper.eq("t.state", requestBody.getState());
		}
		return entityWrapper;
	}

	@ApiOperation(value = "添加校验方式", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult<String> addVerificationManage(@Validated VerificationManage requestBody, BindingResult bindingResult) {
		EntityWrapper<VerificationManage> wrapper = new EntityWrapper<VerificationManage>();
		wrapper.eq("app_id", requestBody.getAppId()).eq("verification_type", requestBody.getVerificationType());
		int count = verificationManageService.selectCount(wrapper);
		if (count > 0) {
			return JsonResult.buildError("该产品已设置该校验方式");
		}
		VerificationManage v = new VerificationManage();
		BeanUtils.copyProperties(requestBody, v);
		boolean res = verificationManageService.insert(v);
		if (res) {
			return JsonResult.buildSuccess("添加校验方式成功");
		}
		return JsonResult.buildError("添加校验方式失败");
	}

	@ApiOperation(value = "修改校验方式", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult<String> updateVerificationManage(@Validated VerificationManage requestBody, BindingResult bindingResult) {
		boolean res = verificationManageService.updateById(requestBody);
		if (res) {
			return JsonResult.buildSuccess("修改校验方式成功");
		}
		return JsonResult.buildError("修改校验方式失败");
	}
	
	@ApiOperation(value = "码系统获取验证方式方法", notes = "")
	@RequestMapping(value = "/getVerificationManage", method = RequestMethod.POST)
	public List<VerificationManage> getVerificationManage(VerificationManage requestBody) {
		// 构建查询条件
		EntityWrapper<VerificationManage> wrapper = new EntityWrapper<VerificationManage>();
		wrapper.eq("app_id", requestBody.getAppId()).eq("state", 1);
		// 查询数据返回数据
		List<VerificationManage> list = verificationManageService.selectList(wrapper);
		return list;
	} 
}
