package com.meizhuang.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.utils.support.HttpKit;

public class FilesOperate {

	/**
	 * 添加文件夹
	 * 
	 * @Description:
	 * @param @param folderPath 文件夹完整绝对路径
	 * @return void
	 */
	public static boolean addFolder(String folderPath) {
		boolean flag = false;
		try {
			File file = new File(folderPath);
			if (file.exists()) {
				flag = true;
			} else {
				flag = file.mkdir();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除文件夹
	 * 
	 * @Description:
	 * @param @param folderPath 文件夹完整绝对路径
	 * @return void
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @Description:
	 * @param @param  path 文件夹完整绝对路径
	 * @param @return
	 * @return boolean
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/***
	 * 下载文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileDownLoad(String path) {
		HttpServletResponse response = HttpKit.getResponse();
		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean downLoadExcel(List<String> paths, String fileName) {
		if (paths != null && paths.size() == 1) {
			return fileDownLoad(paths.get(0));
		}
		// 生成的ZIP文件名为Demo.zip
		String tmpFileName = fileName + "_" + new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date()) + ".zip";
		byte[] buffer = new byte[1024];

		// 新加一个文件
		String proFileStr = System.getProperty("user.dir") + File.separator + "excelTemp";
		addFolder(proFileStr);
		String strZipPath = proFileStr + tmpFileName;

		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
			// 需要同时下载的两个文件result.txt ，source.txt
			File[] file1 = new File[paths.size()];
			for (int i = 0; i < paths.size(); i++) {
				file1[i] = new File(paths.get(i));
			}
			for (int i = 0; i < file1.length; i++) {
				FileInputStream fis = new FileInputStream(file1[i]);
				out.putNextEntry(new ZipEntry(file1[i].getName()));
				// 设置压缩文件内的字符编码，不然会变成乱码
				out.setEncoding("GBK");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
			boolean b = FilesOperate.fileDownLoad(strZipPath);
			delAllFile(proFileStr);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
	}

}
