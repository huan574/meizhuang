package com.meizhuang.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.meizhuang.entity.AdStation;
import com.meizhuang.entity.HtmlArticle;
import com.meizhuang.entity.Proclamation;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.param.request.PageRequest;
import com.meizhuang.result.JsonResult;
import com.meizhuang.result.PageResult;
import com.meizhuang.service.AdStationService;
import com.meizhuang.service.HtmlArticleService;
import com.meizhuang.utils.SystemParameterUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;

@Api(tags = "图片广告接口")
@RestController
@RequestMapping(value = "/flow/adStation")
public class AdStationController {

	@Autowired
	private AdStationService adStationService;
	
	@Autowired
	private HtmlArticleService htmlArticleService;
	
	@ApiOperation(value = "页面跳转", notes = "")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("ad/adStationList");
	}
	
	@ApiOperation(value = "分页查询信息", notes = "")
	@RequestMapping(value = "/getAdStationList", method = RequestMethod.POST)
	public PageResult<AdStation> getAdStationList(PageRequest pageRequest) {
		Page<AdStation> page = new Page<AdStation>(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getSortField(), pageRequest.getAscState());
		adStationService.selectPage(page);
		return PageResult.buildSuccess(page.getTotal(), page.getRecords());
	}
	
	@ApiImplicitParams({ //
		@ApiImplicitParam(value = "上传文件", name = "file", required = true, allowMultiple = true, dataType = "MultipartFile") //
	})
	@ApiOperation(value = "上传广告图", notes = "")
	@RequestMapping(value = "/uploadAdStationImg", method = RequestMethod.POST)
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
	    	filePath = filePath +File.separator +AdStation.AD_STATION_FILE_NAME+File.separator;
	    	 
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
	
	@ApiOperation(value = "增加广告图", notes = "")
	@RequestMapping(value = "/addAdStation", method = RequestMethod.POST)
	public JsonResult<String> addAdStation(
			AdStation requestBody){

		requestBody.setState(2);
		boolean result = adStationService.insert(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("增加广告图失败");
		}
	}
	
	@ApiOperation(value = "删除广告图", notes = "")
	@RequestMapping(value = "/delAdStation", method = RequestMethod.POST)
	public JsonResult<String> delAdStation(@RequestParam(value = "id", required = true) Integer id){
		boolean result =adStationService.deleteById(id);
		
		
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("删除广告图失败");
		}
	}
	
	@ApiOperation(value = "修改广告图", notes = "")
	@RequestMapping(value = "/updateAdStation", method = RequestMethod.POST)
	public JsonResult<String> updateAdStation(AdStation requestBody){
		
		if(requestBody.getClickResult() == 1){
			Integer h5Id = requestBody.getH5Id();
			HtmlArticle html = htmlArticleService.selectById(h5Id);
			if(html == null){
				return JsonResult.buildError("没有找到H5页面");
			}
		}else{
			requestBody.setH5Id(0);
		}
		
		boolean result = adStationService.updateById(requestBody);
		if(result){
			return JsonResult.buildSuccess();
		}else{
			return JsonResult.buildError("更新广告失败");
		}
	}
	
}
