package com.meizhuang.utils.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meizhuang.utils.excel.ExcelHeader;
import com.meizhuang.utils.excel.ExcelResources;
import com.meizhuang.utils.excel.POIExcelUtil;
import com.meizhuang.utils.file.FileUtil;

/**
 * CSV操作(导出和导入)
 * 
 * @author HuangBo
 *
 */
public class CSVUtil {

	// private static Logger log = Logger.getLogger(CSVUtil.class.getName());
	public static String path = CSVUtil.class.getResource("/").getPath();// 类路径

	private CSVUtil() {

	}

	public static CSVUtil getInstance() {
		return Instance.instance;
	}

	private static class Instance {
		private static CSVUtil instance = new CSVUtil();
	}

	/**
	 * 第一个字符转换小写
	 * 
	 * @param str
	 * @return
	 */
	public String initlet(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] = (char) (ch[0] + 32);
		}
		return new String(ch);
	}

	@SuppressWarnings("rawtypes")
	public String getCSVFileName(Class clz, Integer page, String extension) {
		String absolutePath = path + "csvTemp\\";
		FileUtil.checkFolderPathIsExists(absolutePath); // 检查目录是否存在
		String fileName = FileUtil.getFileName(clz, page);
		return absolutePath + fileName + "." + extension;
	}

	/**
	 * 导出
	 * 
	 * @param objs
	 * @param clz
	 * @param page
	 * @param charsetName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String exportCsv(List objs, Class clz, Integer page, String charsetName) {
		String absolutePath = path + "csvTemp\\";
		FileUtil.checkFolderPathIsExists(absolutePath); // 检查目录是否存在
		String fileName = getCSVFileName(clz, page, "csv");
		File file = new File(fileName);
		boolean isSucess = exportCsv(file, objs, clz, charsetName);
		if (isSucess) {
			return fileName;
		}
		return null;
	}

	/**
	 * 导出
	 * 
	 * @param objs
	 * @param clz
	 * @param page
	 * @param fileName
	 * @param charsetName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String exportCsv(List objs, Class clz, String fileName, String charsetName) {
		String absolutePath = path + "csvTemp\\";
		FileUtil.checkFolderPathIsExists(absolutePath); // 检查目录是否存在
		File file = new File(fileName);
		boolean isSucess = exportCsv(file, objs, clz, charsetName);
		if (isSucess) {
			return fileName;
		}
		return null;
	}

	/**
	 * 导出
	 * 
	 * @param file
	 * @param objs
	 * @param clz
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean exportCsv(File file, List objs, Class clz, String charsetName) {
		boolean isSucess = false;
		List<ExcelHeader> headers = POIExcelUtil.getInstance().getHeaderList(clz);
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, charsetName);
			bw = new BufferedWriter(osw);
			StringBuffer header = new StringBuffer();
			for (int j = 0; j < headers.size(); j++) {
				ExcelHeader eh = headers.get(j);
				if (j == headers.size() - 1) {
					header.append(eh.getTitle());
				} else {
					header.append(eh.getTitle() + ",");
				}
			}
			bw.append(header.toString()).append("\r\n");
			if (objs != null && !objs.isEmpty()) {
				for (int i = 0; i < objs.size(); i++) {
					StringBuffer values = new StringBuffer();
					Object obj = objs.get(i);
					String value = "";
					for (int j = 0; j < headers.size(); j++) {
						ExcelHeader eh = headers.get(j);
						String mn = eh.getMethodName();
						Method m = clz.getMethod(mn);
						Object rel = m.invoke(obj);
						if (rel == null) {
							value = "暂无";
						} else {
							value = rel.toString().trim();
						}
						if (j == headers.size() - 1) {
							values.append(value);
						} else {
							values.append(value + ",");
						}
					}
					bw.append(values.toString()).append("\r\n");
				}
			}
			if (file.exists()) {
				isSucess = true;
			} else {
				isSucess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}

	/**
	 * 导入
	 * 
	 * @param file csv文件(路径+文件)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List importCsv(Class cls, File file, String charsetName) {
		List list = new ArrayList();
		Map<String, String> map = new HashMap<String, String>();// 对应字段的类型
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>(); // 要设置的字段

		Field[] fields = cls.getDeclaredFields(); // 获取类下的所有字段
		for (int i = 0; i < fields.length; i++) {
			String filedName = fields[i].getName();
			fields[i].setAccessible(true); // 设置可以拿到私有的字段
			Class<?> type = fields[i].getType();
			String typeName = type.getName();
			map.put(filedName, typeName);
		}
		Method[] method = cls.getMethods();
		for (Method m : method) {
			String mn = m.getName();
			if (mn.startsWith("set")) {
				if (m.isAnnotationPresent(ExcelResources.class)) {
					ExcelResources er = m.getAnnotation(ExcelResources.class);
					headers.add(new ExcelHeader(er.title(), er.order(), mn));
				}
			}
		}
		BufferedReader br = null;
		try {
			int lineCount = 0;
			int cellCount = 0;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
			String line = "";
			while ((line = br.readLine()) != null) { // 每一行
				Object obj = cls.newInstance();
				String[] cellNum = line.split(",");
				if (lineCount == 0) { // 第一行，表头
					cellCount = cellNum.length; // 表头列的数量
					int titleCount = 0;
					for (int i = 0; i < cellNum.length; i++) {
						String titleName = cellNum[i].toString().trim();
						for (ExcelHeader excelHeader : headers) {
							if (titleName.equals(excelHeader.getTitle())) {
								excelHeader.setOrder(i);
								titleCount++; // 有匹配的表头
							}
						}
					}
					if (titleCount == 0) { // 没有一个匹配的表头
						return list;
					}
				} else { // 内容
					if (cellCount == cellNum.length) {
						for (int i = 0; i < cellNum.length; i++) {
							for (ExcelHeader excelHeader : headers) {
								if (i == excelHeader.getOrder()) {
									Field field = obj.getClass().getDeclaredField(initlet(excelHeader.getMethodName().replace("set", "").toString()));
									Method methodd = obj.getClass().getMethod(excelHeader.getMethodName(), field.getType());
									String type = map.get(initlet(excelHeader.getMethodName().replace("set", "").toString()));
									String value = cellNum[i].toString().trim();
									if (value != null) {
										if ("int".equals(type) || "java.lang.Integer".equals(type)) {
											methodd.invoke(obj, Double.valueOf(value.toString().trim()).intValue());
										} else if ("long".equals(type) || "java.lang.Long".equals(type)) {
											methodd.invoke(obj, Double.valueOf(value.toString().trim()).intValue());
										} else if ("double".equals(type) || "java.lang.Double".equals(type)) {
											methodd.invoke(obj, Double.valueOf(value.toString().trim()));
										} else {
											methodd.invoke(obj, String.valueOf(value.toString().trim()));
										}
									}
								}
							}
						}
						list.add(obj);
					}
				}
				lineCount++;
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 导出
	 * 
	 * @param file     csv文件(路径+文件名)，csv文件不存在会自动创建
	 * @param dataList 数据
	 * @return
	 */
	public boolean exportCsv(File file, List<String> dataList, String charsetName) {
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, charsetName);
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r\n");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}

	/**
	 * 导入
	 * 
	 * @param file csv文件(路径+文件)
	 * @return
	 */
	public List<String> importCsv(File file, String charsetName) {
		List<String> dataList = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

}
