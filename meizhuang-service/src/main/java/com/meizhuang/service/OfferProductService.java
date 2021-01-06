package com.meizhuang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meizhuang.entity.OfferProduct;
import com.meizhuang.mapper.meizhuang.OfferProductMapper;

@Service
public class OfferProductService extends BaseService<OfferProductMapper,OfferProduct>{
	
	@Autowired
	private OfferProductMapper mapper;
	
	public List<OfferProduct> searchByKey(List<String> keys){
		return mapper.searchByKey(keys);
	}
}
