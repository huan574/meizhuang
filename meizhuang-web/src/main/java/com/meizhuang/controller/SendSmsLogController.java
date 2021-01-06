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
import com.meizhuang.entity.SendSmsLog;
import com.meizhuang.param.request.PageRequest;
//import com.meizhuang.param.request.system.GetSendSmsLogsRequest;
import com.meizhuang.result.PageResult;
//import com.meizhuang.service.SendSmsLogService;


@RestController
@RequestMapping(value = "/meizhuang/sendSmsLog")
public class SendSmsLogController extends BaseController {

//	@Autowired
//	private SendSmsLogService sendSmsLogService;
//
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		return new ModelAndView("log/sendSmsLog");
//	}
//
//	@RequestMapping(value = "/getSendSmsLogs", method = RequestMethod.POST)
//	public PageResult<SendSmsLog> getSendSmsLogs(PageRequest pageRequest, GetSendSmsLogsRequest requestBody) {
//		Page<SendSmsLog> page = new Page<SendSmsLog>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
//		// 构建查询条件
//		EntityWrapper<SendSmsLog> wrapper = buildWrapper(requestBody);
//		// 查询数据返回总记录数和数据
//		sendSmsLogService.selectPage(page, wrapper);
//		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
//	}
//
//	private EntityWrapper<SendSmsLog> buildWrapper(GetSendSmsLogsRequest requestBody) {
//		EntityWrapper<SendSmsLog> entityWrapper = new EntityWrapper<SendSmsLog>();
//
//		if (StringUtils.isNotEmpty(requestBody.getMobile())) {
//			entityWrapper.eq("mobile", requestBody.getMobile());
//		}
//
//		if (StringUtils.isNotEmpty(requestBody.getMsg())) {
//			entityWrapper.like("msg", requestBody.getMsg());
//		}
//
//		if (StringUtils.isNotEmpty(requestBody.getCtime())) {
//			String[] ctime = requestBody.getCtime().split("~");
//			if (ctime.length == 2) {
//				entityWrapper.ge("ctime", ctime[0]);
//				entityWrapper.le("ctime", ctime[1]);
//			}
//		}
//
//		return entityWrapper;
//	}

}
