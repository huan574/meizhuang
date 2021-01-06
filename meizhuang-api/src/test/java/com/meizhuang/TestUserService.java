package com.meizhuang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

	//@Autowired
	//private UserService userService;

	@Test
	public void testSelectAll() throws Exception {
		Page<User> page = new Page<User>(1, 10, "ctime", false);
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
		// entityWrapper.eq("resource_url", "/users/a"); // 参考 https://baomidou.gitee.io/mybatis-plus-doc/#/wrapper
		entityWrapper.setEntity(new User.Builder().userType((byte) 1).build());
//		Page<User> pageList = userService.selectPage(page, entityWrapper);
//
//		System.out.println(pageList.toString());
//		for (User user : pageList.getRecords()) {
//			System.out.println(user);
//		}
		
		
		
		
	}
	
	//获得数字串工具类
    public static List<String> getFullNumFromString(String str){
        List<String> resultList = new ArrayList<>();
        StringBuilder numBuilder = new StringBuilder();
        str = str.replaceAll(" ","");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 48 && ch <= 57) {
                numBuilder.append(ch);
                if ( i == str.length() -1) {
                    resultList.add(numBuilder.toString());
                }
            } else {
                if (!numBuilder.toString().equals("") && numBuilder.length() > 0) {
                    resultList.add(numBuilder.toString());
                    numBuilder = new StringBuilder();
                }
            }
        }
        return resultList;
    }
	public static void main(String[] args) {
		  String a="love23next234csdn3423javaeye";
	
		System.out.print(getFullNumFromString(a));
		
		System.out.println(RandomStringUtils.randomNumeric(9));
		
		Map<String, List<Integer>> list  = new HashMap<String, List<Integer>>();
		for(Map.Entry<String, List<Integer>> item : list.entrySet()){
		}
    }
	

}
