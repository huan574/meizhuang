package com.meizhuang.mapper.meizhuang;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.meizhuang.entity.Token;
import com.meizhuang.mapper.base.Mapper;

public interface TokenMapper extends Mapper<Token>{

	
	
	@Select("SELECT a.* , TIMESTAMPDIFF(MINUTE, ctime, NOW())  as login_minute_time from token a  where  uid=#{uid} ")
	Token queryLoginMinuteTime(@Param(value = "uid") Integer uid);
}
