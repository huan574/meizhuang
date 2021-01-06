package com.meizhuang.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.meizhuang.config.properties.PropertiesUtils;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.result.JsonResult;

public class OssClient {

	private static String ossBasePath = PropertiesUtils.getProperty("ossBasePath");
	private static String accessKeyId = PropertiesUtils.getProperty("accessKeyId");
	private static String accessKeySecret = PropertiesUtils.getProperty("accessKeySecret");
	private static String endpoint = PropertiesUtils.getProperty("endpoint");
	private static String bucketName = PropertiesUtils.getProperty("bucketName");

	private static OSS ossClient;

	public static OSS getossClient() {
		return ossClient;
	}

	static {
		ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
		// 设置HTTP最大连接数为20
		conf.setMaxConnections(20);
		// 设置TCP连接超时为5000毫秒
		conf.setConnectionTimeout(10000);
		// 设置最大的重试次数为5
		conf.setMaxErrorRetry(5);
		// 设置Socket传输数据超时的时间为5000毫秒
		conf.setSocketTimeout(10000);
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
	}

	/**
	 * 上传文件到阿里云OSS的文件（上传之后默认删除本地文件）
	 * 
	 * @param localFilePath本地文件路径
	 * @return
	 */
	public static JsonResult<String> uploadFile(String localFilePath) {
		return uploadFile(localFilePath, true);
	}

	/**
	 * 上传文件到阿里云OSS的文件
	 * 
	 * @param localFilePath本地文件路径
	 * @param isDeleteFile是否上传之后删除本地文件
	 * @return
	 */
	public static JsonResult<String> uploadFile(String localFilePath, boolean isDeleteFile) {
		try {
			File file = new File(localFilePath);
			if (!file.isFile()) { // 文件不存在
				return JsonResult.buildError(BizExceptionEnum.FILE_NOT_FOUND);
			}
			String fileName = new StringBuilder().append(UUID.randomUUID().toString()).append(localFilePath.substring(localFilePath.lastIndexOf(".")).toLowerCase()).toString();
			InputStream content;
			content = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());
			// 上传Object.
			ossClient.putObject(bucketName, fileName, content, meta);
			fileName = ossBasePath + fileName;
			if (isDeleteFile) { // 删除本地文件
				file.delete();
			}
			return JsonResult.buildSuccess("上传成功", fileName);
		} catch (FileNotFoundException e) {
			throw new BussinessException(BizExceptionEnum.FILE_NOT_FOUND);
		}
	}

	public static JsonResult<String> uploadFile(InputStream stream, String formatName) {
		try {
			String fileName = new StringBuilder().append(UUID.randomUUID().toString()).append(".").append(formatName.toLowerCase()).toString();
			InputStream content = stream;
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(content.available());
			String contentType = getContentType(fileName);
			if (contentType != null) {
				meta.setContentType(contentType);
			}
			// 上传Object.
			ossClient.putObject(bucketName, fileName, content, meta);
			fileName = ossBasePath + fileName;
			return JsonResult.buildSuccess("上传成功", fileName);
		} catch (Exception e) {
			throw new BussinessException(BizExceptionEnum.FILE_NOT_FOUND);
		}
	}

	/**
	 * 删除阿里云OSS的文件
	 * 
	 * @param fileName文件名称
	 */
	public static void deleteFile(String fileName) {
		ossClient.deleteObject(bucketName, fileName);
	}

	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * 
	 * @param fileName文件名
	 * @return 文件的contentType
	 */
	public static final String getContentType(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if ("bmp".equalsIgnoreCase(fileExtension)) {
			return "image/bmp";
		} else if ("gif".equalsIgnoreCase(fileExtension)) {
			return "image/gif";
		} else if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension)) {
			return "image/jpeg";
		} else if ("html".equalsIgnoreCase(fileExtension)) {
			return "text/html";
		} else if ("txt".equalsIgnoreCase(fileExtension)) {
			return "text/plain";
		} else if ("vsd".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.visio";
		} else if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.ms-powerpoint";
		} else if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
			return "application/msword";
		} else if ("xml".equalsIgnoreCase(fileExtension)) {
			return "text/xml";
		}
		return null;
	}

}
