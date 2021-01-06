package com.meizhuang.utils.file;

import java.io.File;
import java.util.Date;

public class FileUtil {

	/**
	 * 判断文件夹目录是否存在，不存在则创建
	 * 
	 * @param folderPath
	 */
	public static void checkFolderPathIsExists(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
	}

	/**
	 * 获取文件名字
	 * 
	 * @param clz
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getFileName(Class clz, Integer page) {
		if (page == null) {
			return "BATCH_" + clz.getSimpleName().toUpperCase() + "_" + new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		}
		return "BATCH_" + clz.getSimpleName().toUpperCase() + "_" + new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "_" + page;
	}

}
