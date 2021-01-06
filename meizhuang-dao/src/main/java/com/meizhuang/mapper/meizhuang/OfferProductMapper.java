package com.meizhuang.mapper.meizhuang;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.meizhuang.entity.OfferProduct;
import com.meizhuang.mapper.base.Mapper;

public interface OfferProductMapper extends Mapper<OfferProduct>{
	
	@Select({
        "<script>",
            "select *from",
            "(select o.*,u.mobile,u.weixin,u.weixin_img,u.store_name,u.store_remark,u.trade_stall,u.random from offer_product o ",
            "inner join user_info u on(o.uid = u.uid) where   ",
            "<foreach collection='keys' item='key' open='(' separator='and' close=')'>",
             "o.offer_product like '%${key}%' ",
             "</foreach>",
             "having 1=1 order by o.ctime desc) ",
            "as d group by uid,d.product order by d.ctime desc limit 380",
        "</script>"
	})
	List<OfferProduct> searchByKey(@Param("keys")  List<String> keys);
	

}
