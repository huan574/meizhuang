package com.meizhuang.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.SysException;
import com.meizhuang.param.request.GetSysExceptionsRequest;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.SysExceptionService;


@RestController
@RequestMapping(value = "/flow/sysException")
public class SysExceptionController extends BaseController {

	@Autowired
	private SysExceptionService sysExceptionService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("log/sysException");
	}

	@RequestMapping(value = "/getSysExceptions", method = RequestMethod.POST)
	public PageResult<SysException> getSysExceptions(PageRequest pageRequest, GetSysExceptionsRequest requestBody) {
		Page<SysException> page = new Page<SysException>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		EntityWrapper<SysException> wrapper = buildWrapper(requestBody);
		// 查询数据返回总记录数和数据
		sysExceptionService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	private EntityWrapper<SysException> buildWrapper(GetSysExceptionsRequest requestBody) {
		EntityWrapper<SysException> entityWrapper = new EntityWrapper<SysException>();

		if (StringUtils.isNotEmpty(requestBody.getIpAddress())) {
			entityWrapper.eq("ip_address", requestBody.getIpAddress());
		}

		if (StringUtils.isNotEmpty(requestBody.getResourceUrl())) {
			entityWrapper.eq("resource_url", requestBody.getResourceUrl());
		}

		if (StringUtils.isNotEmpty(requestBody.getUseSystem())) {
			entityWrapper.eq("use_system", requestBody.getUseSystem());
		}

		if (StringUtils.isNotEmpty(requestBody.getSysContents())) {
			entityWrapper.like("sys_contents", requestBody.getSysContents());
		}

		if (StringUtils.isNotEmpty(requestBody.getCtime())) {
			String[] ctime = requestBody.getCtime().split("~");
			if (ctime.length == 2) {
				entityWrapper.ge("ctime", ctime[0]);
				entityWrapper.le("ctime", ctime[1]);
			}
		}

		return entityWrapper;
	}

}
