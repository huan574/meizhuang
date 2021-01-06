package com.meizhuang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meizhuang.base.BaseController;
import com.meizhuang.entity.enums.SystemParameterEnum;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.SystemParameterUtils;
import com.meizhuang.utils.file.OssClient;

@Api(tags = "文件上传相关接口")
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

	@ApiImplicitParams({ //
		@ApiImplicitParam(value = "上传文件", name = "file", required = true, allowMultiple = true, dataType = "MultipartFile") //
	})
	@ApiOperation(value = "上传图片", notes = "上传图片接口")
	@PostMapping(value = "/uploadImageToYun")
	public JsonResult<String> uploadImageToYun( //
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
		String path = System.getProperty("user.dir") + File.separator + "upload";
		String fileName = path + File.separator + multipartFile.getOriginalFilename();// 文件名
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		file = new File(fileName);
		try {
			multipartFile.transferTo(file);// 保存到一个目标文件中。
		} catch (Exception e) {
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
		return OssClient.uploadFile(fileName);
	}

	//使用流将图片输出
    @GetMapping("/getImage/{name}/")
    public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
    	
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }
    
  //使用流将图片输出
    @GetMapping("/getImage/{pathName}/{name}/")
    public void getImage(HttpServletResponse response, @PathVariable("pathName") String pathName, @PathVariable("name") String name) throws IOException {
    	
    	String filePath = SystemParameterUtils.get(SystemParameterEnum.FILE_SAVE_ROOT_PATH);
    	
    	if(StringUtils.isBlank(filePath)){
    		return;
    	}
    	filePath = filePath +File.separator + pathName +File.separator;
    	
    	
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(filePath).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }
	
}
