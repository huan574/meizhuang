package com.meizhuang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.CategoryClass;

import com.meizhuang.service.CategoryClassService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class CategoryController extends BaseController {

	@Autowired
	private CategoryClassService categoryClassService;
	
	@RequestMapping(value = "/categoryClass")
	public ModelAndView categoryClass(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		Integer fid = null;
		Integer sid = null;
		
		List<CategoryClass> fList = null;
		List<CategoryClass> sList = null;
		List<CategoryClass> tList = null;
		
		EntityWrapper<CategoryClass> entityWrapper = new EntityWrapper<CategoryClass>();
		entityWrapper.eq("type", 1);
		fList = categoryClassService.selectList(entityWrapper);
		
		if(StringUtils.isNotBlank(id)){
			EntityWrapper<CategoryClass> wrapper = new EntityWrapper<CategoryClass>();
			wrapper.eq("id", id);
			
			CategoryClass categoryClass = categoryClassService.selectOne(wrapper);
			if(categoryClass != null){
				
				if(categoryClass.getType() == 1){
					fid = categoryClass.getId();
					sList = getSCategoryClass(fid);
					
				}else if(categoryClass.getType() == 2){
					sid= categoryClass.getId();
					categoryClass = getPCategoryClass(categoryClass.getpId());
					if(categoryClass != null){
						fid = categoryClass.getId();
						
					}
					tList = getSCategoryClass(sid);
				}
			}
		}else{
			if(fList.size() > 0){
				CategoryClass categoryClass = fList.get(0);
				fid = categoryClass.getId();
				sList = getSCategoryClass(fid);
			}
		}
		
		
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("fCategoryList", fList);
		request.setAttribute("sCategoryList", sList);
		request.setAttribute("tCategoryList", tList);
		
		return new ModelAndView("category");
	}
	
	
	private List<CategoryClass> getSCategoryClass(Integer id){
		EntityWrapper<CategoryClass> wrapper = new EntityWrapper<CategoryClass>();
		wrapper.eq("p_id", id);
		List<CategoryClass> categoryClassList = categoryClassService.selectList(wrapper);
		return categoryClassList;
	}
	
	private CategoryClass getPCategoryClass(Integer pId){
		EntityWrapper<CategoryClass> wrapper = new EntityWrapper<CategoryClass>();
		wrapper.eq("id", pId);
		CategoryClass categoryClass = categoryClassService.selectOne(wrapper);
		return categoryClass;
	}
	
	
	
	
	
	
}
