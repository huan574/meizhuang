package com.meizhuang.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.meizhuang.base.BaseController;
import com.meizhuang.entity.AdStation;
import com.meizhuang.entity.CategoryClass;
import com.meizhuang.entity.MarketClass;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.mapper.base.BaseWrapper;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.CategoryClassService;
import com.meizhuang.service.MarketClassService;
import com.meizhuang.utils.SystemParameterUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;

@Api(tags = "产品库接口")
@RestController
@RequestMapping(value = "/flow/categoryClass")
public class CategoryClassController extends BaseController {

	@Autowired
	private CategoryClassService categoryClassService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("category/categoryClassList");
	}
	
	@ApiImplicitParams({ //
		@ApiImplicitParam(value = "上传文件", name = "file", required = true, allowMultiple = true, dataType = "MultipartFile") //
	})
	@ApiOperation(value = "上传广告图", notes = "")
	@RequestMapping(value = "/uploadCategoryClassImg", method = RequestMethod.POST)
	public JsonResult<String> uploadAdStationImg(@RequestParam(value = "file", required = true) MultipartFile multipartFile){
		String newFileName = null;
		if(multipartFile != null && !multipartFile.isEmpty()){
			// 检查是否是图片
			try {
				BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
				if (bi == null) {
					return JsonResult.buildError("请上传图片格式的文件");
				}
			} catch (IOException e) {
				return JsonResult.buildError("请上传图片格式的文件");
			}
			
			String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
	    	
	    	if(StringUtils.isBlank(filePath)){
	    		return JsonResult.buildError("未配置filePath");
	    	}
	    	filePath = filePath +File.separator +CategoryClass.CATEGORY_FILE_NAME+File.separator;
	    	 
	    	try{
	    		InputStream inputStream = multipartFile.getInputStream();
	    		String fileName = multipartFile.getOriginalFilename();
	            Path directory = Paths.get(filePath);
	            if (!Files.exists(directory)) {
	                Files.createDirectories(directory);
	            }
	            newFileName = UUID.randomUUID().toString()+ fileName.substring(fileName.lastIndexOf("."));
	            long copy = Files.copy(inputStream, directory.resolve(newFileName));
	            Thumbnails.of(filePath+newFileName).scale(0.8f).toFile(filePath+newFileName);//按比例缩小

	    	}catch(Exception e){
	    		return JsonResult.buildError("图片上传失败");
	    	}
			
		}
		return JsonResult.buildSuccess("上传成功", newFileName);

	}
	
	@ApiOperation(value = "分页查询信息", notes = "")
	@RequestMapping(value = "/getCategoryClassList", method = RequestMethod.POST)
	public PageResult<CategoryClass> getCategoryClassList(CategoryClass request, PageRequest pageRequest) {
		
		BaseWrapper<CategoryClass> baseWrapper = new BaseWrapper<CategoryClass>(CategoryClass.selectFiled, CategoryClass.fromFiled);
		if(!StringUtils.isBlank(request.getClassName())){
			baseWrapper.like("m1.class_name", request.getClassName());
		}
		if(request.getType() != null){
			baseWrapper.eq("m1.type", request.getType());
		}
		
		Page<CategoryClass> page = new Page<CategoryClass>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		categoryClassService.selectPage(page,baseWrapper);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	@ApiOperation(value = "增加", notes = "")
	@RequestMapping(value = "/addCategoryClass", method = RequestMethod.POST)
	public JsonResult<String> addCategoryClass(
			CategoryClass requestBody){

		if((requestBody.getType()== null || requestBody.getType() == 0)){
			return JsonResult.buildError("请选择具体类型");
		}
		boolean result = categoryClassService.insert(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("增加失败");
		}
	}
	
	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/delCategoryClass", method = RequestMethod.POST)
	public JsonResult<String> delCategoryClass(@RequestParam(value = "ids", required = true) String ids){
		
		String[] idSp = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		
		for(String idStr : idSp){
			
			
			Integer id = Integer.valueOf(idStr);
			if(id<0){
				continue;
			}
			idList.add(id);
			
			CategoryClass clazz = categoryClassService.selectById(id);
			if(clazz == null){
				return JsonResult.buildError("数据有误");
			}
			
			EntityWrapper<CategoryClass> entityWrapper = new EntityWrapper<CategoryClass>();
			entityWrapper.eq("p_id", id);
			
			int count = categoryClassService.selectCount(entityWrapper);
			
			if(count > 0){
				return JsonResult.buildError("请先删除关联的子分类");
			}
			
		}
		
		
		boolean result =categoryClassService.deleteBatchIds(idList);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("删除失败");
		}
	}
	
	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/updateCategoryClass", method = RequestMethod.POST)
	public JsonResult<String> updateCategoryClass(CategoryClass requestBody){
		
		CategoryClass upCategoryClass = new CategoryClass();
		upCategoryClass.setId(requestBody.getId());
		upCategoryClass.setClassName(requestBody.getClassName());
		upCategoryClass.setImg(requestBody.getImg());
		
		boolean result = categoryClassService.updateById(upCategoryClass);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("更新失败");
		}
	}
	
	@ApiOperation(value = "查询市场", notes = "")
	@RequestMapping(value = "/getParentTypeCategoryClass", method = RequestMethod.POST)
	public JsonResult<List<CategoryClass>> getParentTypeCategoryClass(@RequestParam(value = "type", required = true) Integer type){
		
		BaseWrapper<CategoryClass> entityWrapper = new BaseWrapper<CategoryClass>(CategoryClass.selectFiled, CategoryClass.fromFiled);
		
		entityWrapper.eq("m1.type", type);
		
		List<CategoryClass> list = categoryClassService.selectByList(entityWrapper);
		
		return JsonResult.buildSuccess(list);
		
	}
	
	@ApiOperation(value = "查询市场", notes = "")
	@RequestMapping(value = "/getParentIdCategoryClass", method = RequestMethod.POST)
	public JsonResult<List<CategoryClass>> getParentIdCategoryClass(@RequestParam(value = "pid", required = true) Integer pid){
		
		EntityWrapper<CategoryClass> entityWrapper = new EntityWrapper<CategoryClass>();
		
		entityWrapper.eq("p_id", pid);
		
		List<CategoryClass> list = categoryClassService.selectList(entityWrapper);
		
		return JsonResult.buildSuccess(list);
		
	}
	
	
}
