package com.meizhuang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meizhuang.entity.MarketClass;
import com.meizhuang.mapper.meizhuang.MarketClassMapper;

@Service
public class MarketClassService extends BaseService<MarketClassMapper,MarketClass>{

	@Autowired
	private MarketClassMapper mapper;
	
	
}
