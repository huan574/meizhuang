package com.meizhuang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meizhuang.base.BaseController;
import com.meizhuang.entity.CategoryClass;
import com.meizhuang.entity.IdentifyInfo;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.SystemParameterUtils;
import com.meizhuang.utils.file.OssClient;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "文件上传相关接口")
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

	
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private static String UPLOAD_PATH = "File/upload";
	
	private static String SYSTEM_UPLOAD_PATH = "D:/file/upload";
	
	@ApiImplicitParams({ //
		@ApiImplicitParam(value = "用户编号", name = "uid", required = true), //
		@ApiImplicitParam(value = "上传文件", name = "file", required = true, allowMultiple = true, dataType = "MultipartFile") //
	})
	@ApiOperation(value = "上传图片", notes = "上传图片接口")
	@PostMapping(value = "/uploadImageToLocal")
	public JsonResult<String> uploadImageToLocal( //
			@RequestParam(value = "uid", required = true) Integer uid, //
			@RequestParam(value = "file", required = true) MultipartFile multipartFile //
	) {
		// 检查是否是图片
		try {
			BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
			if (bi == null) {
				return JsonResult.buildError(BizExceptionEnum.FILE_IMAGE_FORMAT_ERROR);
			}
		} catch (IOException e) {
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
        String path = System.getProperty("user.dir") + File.separator + UPLOAD_PATH;
        // 文件名
        String fileName = path + File.separator + multipartFile.getOriginalFilename();
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        file = new File(fileName);
        try {
            // 保存到一个目标文件中
            multipartFile.transferTo(file);
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
        }
        
        return JsonResult.buildSuccess("上传成功",file.getName());
        
       
    }

	
	  //上传图片
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> uploadImage(MultipartFile image) {
        try {
        	
            String name = image.getOriginalFilename();

            System.out.println(name);

            InputStream inputStream = image.getInputStream();
            Path directory = Paths.get(SYSTEM_UPLOAD_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            long copy = Files.copy(inputStream, directory.resolve(name));

            return JsonResult.buildSuccess("上传成功",name);

        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	
            return JsonResult.buildError("上传失败");
        }

    }

    //使用流将图片输出
    @GetMapping("/getImage/{name}/")
    public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	if(StringUtils.isBlank(name)){
    		return;
    	}
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        try{
        	ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
        	
        }
        
    }
	
  //使用流将图片输出
    @GetMapping("/getImage/ad/{name}/")
    public void getImageAd(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	
    	if(StringUtils.isBlank(name)){
    		return;
    	}
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator + "ad" +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        try{
	        ServletOutputStream outputStream = response.getOutputStream();
	        outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
	        outputStream.flush();
	        outputStream.close();
	    }catch(Exception e){
	    	
	    }
    }
    
  //使用流将图片输出
    @GetMapping("/getImage/logo/{name}/")
    public void getImageHead(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	
    	if(StringUtils.isBlank(name)){
    		return;
    	}
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator + "logo" +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        try{
        	Path path= Paths.get(filePath).resolve(name);
            outputStream.write(Files.readAllBytes(path));
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
        	logger.error(e.toString());
        }
        
    }
    
  //使用流将图片输出
    @GetMapping("/getImage/identify/{name}/")
    public void getImageIdentify(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	
    	if(StringUtils.isBlank(name)){
    		return;
    	}
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator + IdentifyInfo.IDENTIFY_FILE_NAME +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        try{
        	Path path= Paths.get(filePath).resolve(name);
            outputStream.write(Files.readAllBytes(path));
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
        	logger.error(e.toString());
        }
        
    }
    
    
  //使用流将图片输出
    @GetMapping("/getImage/category/{name}/")
    public void getImageCategory(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	
    	if(StringUtils.isBlank(name)){
    		return;
    	}
    	
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator + CategoryClass.CATEGORY_FILE_NAME +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        try{
        	Path path= Paths.get(filePath).resolve(name);
            outputStream.write(Files.readAllBytes(path));
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
        	logger.error(e.toString());
        }
        
    }
    
    
    
}
