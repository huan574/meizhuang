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
import com.meizhuang.entity.SystemLog;
import com.meizhuang.param.request.GetSystemLogRequest;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.SystemLogService;

@RestController
@RequestMapping(value = "/flow/systemLog")
public class SystemLogController extends BaseController {

	@Autowired
	private SystemLogService systemLogService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("log/systemLog");
	}

	@RequestMapping(value = "/getSystemLogs", method = RequestMethod.POST)
	public PageResult<SystemLog> getSystemLogs(PageRequest pageRequest, GetSystemLogRequest requestBody) {
		Page<SystemLog> page = new Page<SystemLog>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		// 构建查询条件
		EntityWrapper<SystemLog> wrapper = buildWrapper(requestBody);
		// 查询数据返回总记录数和数据
		systemLogService.selectPage(page, wrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}

	private EntityWrapper<SystemLog> buildWrapper(GetSystemLogRequest requestBody) {
		EntityWrapper<SystemLog> entityWrapper = new EntityWrapper<SystemLog>();

		if (StringUtils.isNotEmpty(requestBody.getOperationType())) {
			entityWrapper.eq("operation_type", requestBody.getOperationType());
		}
		if (StringUtils.isNotEmpty(requestBody.getContent())) {
			entityWrapper.like("content", requestBody.getContent());
		}

		if (StringUtils.isNotEmpty(requestBody.getUrl())) {
			entityWrapper.eq("url", requestBody.getUrl());
		}

		if (StringUtils.isNotEmpty(requestBody.getMethod())) {
			entityWrapper.like("method", requestBody.getMethod());
		}

		if (StringUtils.isNotEmpty(requestBody.getParams())) {
			entityWrapper.like("params", requestBody.getParams());
		}

		if (StringUtils.isNotEmpty(requestBody.getReturnValue())) {
			entityWrapper.like("return_value", requestBody.getReturnValue());
		}

		if (StringUtils.isNotEmpty(requestBody.getIp())) {
			entityWrapper.eq("ip", requestBody.getIp());
		}

		if (StringUtils.isNotEmpty(requestBody.getCman())) {
			entityWrapper.eq("cman", requestBody.getCman());
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
