package com.meizhuang.mapper.meizhuang;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.meizhuang.entity.User;
import com.meizhuang.mapper.base.Mapper;

public interface UserMapper extends Mapper<User> {

	@Select("select NOW() as now")
	Date getNowTime();
	
	@Select({
        "<script>",
            "select",
            "*",
            "from user",
            "where uid in",
                "<foreach collection='uids' item='uid' open='(' separator=',' close=')'>",
                "#{uid}",
                "</foreach>",
        "</script>"
	})
	List<User> getUserByUids(@Param("uids")  List<Integer> uids);
	
	
	

}
