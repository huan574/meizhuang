package com.meizhuang.utils.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.utils.file.FileUtil;

import cn.hutool.core.date.DatePattern;

public class POIExcelUtil {

	private static final Logger log = LoggerFactory.getLogger(POIExcelUtil.class.getName());

	private POIExcelUtil() {

	}

	public static POIExcelUtil getInstance() {
		return Instance.instance;
	}

	private static class Instance {
		private static POIExcelUtil instance = new POIExcelUtil();
	}

	/**
	 * 获取分批需要处理的次数
	 * 
	 * @param total     总数量
	 * @param batchSize 每次多少条数据
	 * @return
	 */
	public long getBatchCount(long total, long batchSize) {
		long batchCount = 0; // 总共需要执行几次
		if (total % batchSize == 0) {
			batchCount = total / batchSize;
		} else {
			batchCount = total / batchSize + 1;
		}
		return batchCount;
	}

	/***
	 * 得到注解以及方法
	 * 
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ExcelHeader> getHeaderList(Class clz) {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
		Method[] method = clz.getMethods();
		for (Method m : method) {
			String mn = m.getName();
			if (mn.startsWith("get")) {
				if (m.isAnnotationPresent(ExcelResources.class)) {
					ExcelResources er = m.getAnnotation(ExcelResources.class);
					headers.add(new ExcelHeader(er.title(), er.order(), mn));
				}
			}
		}
		Collections.sort(headers);
		return headers;
	}

	/**
	 * 获取Excel表头
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<ExcelHeader> getExcelHeaderList(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			return getExcelHeaderList03(file); // Excel2003
		} catch (Exception e) {
			try {
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				return getExcelHeaderList07(file); // Excel2007
			} catch (Exception e2) {
				throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
			}
		}
	}

	/**
	 * 获取Excel2003表头
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private List<ExcelHeader> getExcelHeaderList03(File file) throws Exception {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>(); // 要设置的字段
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0); // 得到第1个工作薄
			if (sheet == null || sheet.getLastRowNum() == 0) { // 当前没有工作薄或没有数据
				return null;
			}
			HSSFRow row = sheet.getRow(0); // 拿Excel第一行
			if (row == null) {
				return null;
			}
			int cellNum = row.getLastCellNum();
			for (int k = 0; k < cellNum; k++) {
				Object title = row.getCell(k);
				if (title != null) {
					String titleName = row.getCell(k).toString().trim();
					ExcelHeader excelHeader = new ExcelHeader();
					excelHeader.setTitle(titleName);
					excelHeader.setOrder(k);
					headers.add(excelHeader);
				}
			}
			return headers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取Excel2007表头
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private List<ExcelHeader> getExcelHeaderList07(File file) throws Exception {
		List<ExcelHeader> headers = new ArrayList<ExcelHeader>(); // 要设置的字段
		try {
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // 得到第1个工作薄
			if (sheet == null || sheet.getLastRowNum() == 0) { // 当前没有工作薄或没有数据
				return null;
			}
			XSSFRow row = sheet.getRow(0); // 拿Excel第一行
			if (row == null) {
				return null;
			}
			int cellNum = row.getLastCellNum();
			for (int k = 0; k < cellNum; k++) {
				Object title = row.getCell(k);
				if (title != null) {
					String titleName = row.getCell(k).toString().trim();
					ExcelHeader excelHeader = new ExcelHeader();
					excelHeader.setTitle(titleName);
					excelHeader.setOrder(k);
					headers.add(excelHeader);
				}
			}
			return headers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	/**
	 * 获取文件完整路径
	 * 
	 * @param clz
	 * @param page
	 * @param extension 文件扩展名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getExcelFileName(Class clz, Integer page, String extension) {
		String absolutePath = System.getProperty("user.dir") + File.separator + "excelTemp" + File.separator;
		FileUtil.checkFolderPathIsExists(absolutePath); // 检查目录是否存在
		String fileName = FileUtil.getFileName(clz, page);
		return absolutePath + fileName + "." + extension;
	}

	private Workbook getWorkbook() {
		return getWorkbook(DEFAULT_FLUSH_SIZE);
	}

	private Workbook getWorkbook(int flushSize) {
		return new SXSSFWorkbook(flushSize);
	}

	private static final int DEFAULT_FLUSH_SIZE = 300;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Workbook getWorkbookSingleSheet(List objs, Class clz, Map<String, String> datas) throws Exception {
		List<ExcelHeader> headers = getHeaderList(clz);
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet(datas.get("title"));
//		XSSFRow row;
//		XSSFCell cell;
//		XSSFFont font = workbook.createFont();
//		font.setFontHeightInPoints((short) 10);
//		font.setFontName("宋体");
//		XSSFDataFormat format = workbook.createDataFormat();
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFont(font);
//		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//		style.setDataFormat(format.getFormat("@"));
//		row = sheet.createRow(0);

		// 优化...
		Workbook workbook = this.getWorkbook();

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("宋体");
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setDataFormat(workbook.createDataFormat().getFormat("@"));

		Sheet sheet = workbook.createSheet(datas.get("title"));

		Cell cell;
		Row row = sheet.createRow(0);

		for (int j = 0; j < headers.size(); j++) {
			ExcelHeader eh = headers.get(j);
			cell = row.createCell(j);
			cell.setCellStyle(style);
			cell.setCellValue(eh.getTitle());
		}

		ExcelHeader eh;
		Object obj, value;

		for (int i = 0; i < objs.size(); i++) {
			obj = objs.get(i);
			row = sheet.createRow(i + 1);
			for (int j = 0; j < headers.size(); j++) {
				eh = headers.get(j);
				value = clz.getMethod(eh.getMethodName()).invoke(obj);
				cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellValue((String) ((value == null) ? "暂无" : value.toString().trim()));
			}
		}

		for (int j = 0; j < headers.size(); j++) {
			sheet.autoSizeColumn((short) j); // 调整宽度
		}

		return workbook;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Workbook getWorkbookMultipleSheet(List<List> list, Class clz, Map<String, String> datas) throws Exception {
		List<ExcelHeader> headers = getHeaderList(clz);
		Workbook workbook = getWorkbook();

		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("宋体");
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setDataFormat(workbook.createDataFormat().getFormat("@"));

		Row row;
		Cell cell;
		Sheet sheet;

		List objs;
		ExcelHeader eh;
		Object obj, value;

		for (int k = 0; k < list.size(); k++) {
			sheet = workbook.createSheet(datas.get("title") + (k + 1));
			objs = list.get(k);
			row = sheet.createRow(0);

			for (int j = 0; j < headers.size(); j++) {
				eh = headers.get(j);
				cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellValue(eh.getTitle());
			}

			for (int i = 0; i < objs.size(); i++) {
				obj = objs.get(i);
				row = sheet.createRow(i + 1);
				for (int j = 0; j < headers.size(); j++) {
					value = clz.getMethod(headers.get(j).getMethodName()).invoke(obj);
					cell = row.createCell(j);
					cell.setCellStyle(style);
					cell.setCellValue((String) (value == null ? "暂无" : value.toString().trim()));
				}
			}

			for (int j = 0; j < headers.size(); j++) {
				sheet.autoSizeColumn((short) j); // 调整宽度
			}
		}

		return workbook;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getImportList03(Class cls, File[] confirm) throws Exception {
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
		if (confirm != null && confirm.length > 0) {
			for (File excelFile : confirm) {
				try {
					FileInputStream fis = new FileInputStream(excelFile);
					HSSFWorkbook workbook = new HSSFWorkbook(fis);
					int sheetNum = 1;
					for (int i = 0; i < sheetNum; i++) {
						HSSFSheet sheet = workbook.getSheetAt(i); // 得到第0+i个工作薄
						// 当前没有工作薄或没有数据
						if (sheet == null || sheet.getLastRowNum() == 0) {
							continue;
						}
						// 遍历一行中的内容
						for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
							Object obj = cls.newInstance();
							HSSFRow row = sheet.getRow(j);// 得到某一行
							if (row == null) {
								continue;
							}
							int cellNum = row.getLastCellNum();
							if (j == 0) {// 不读第一行
								int titleCount = 0;
								for (int k = 0; k < cellNum; k++) {
									Object title = row.getCell(k);
									if (title != null) {
										String titleName = row.getCell(k).toString().trim();
										for (ExcelHeader excelHeader : headers) {
											if (titleName.equals(excelHeader.getTitle())) {
												excelHeader.setOrder(k);
												titleCount++; // 有匹配的表头
											}
										}
									}
								}
								if (titleCount == 0) { // 没有一个匹配的表头
									return list;
								}
								continue;
							}
							// 标头多少单元格
							try {
								boolean isAdd = false;
								for (int k = 0; k < cellNum; k++) {
									for (ExcelHeader excelHeader : headers) {
										if (k == excelHeader.getOrder()) {
											Field field = obj.getClass().getDeclaredField(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Method methodd = obj.getClass().getMethod(excelHeader.getMethodName(), field.getType());
											String type = map.get(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Object value = row.getCell(k);
											if (value != null && !"".equals(value.toString().trim())) {
												isAdd = true;
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
								if (isAdd) {
									list.add(obj);
								}
							} catch (Exception e) {
								throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getImportListFormatDate03(Class cls, File[] confirm) throws Exception {
		int num = -1;
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
		if (confirm != null && confirm.length > 0) {
			for (File excelFile : confirm) {
				try {
					FileInputStream fis = new FileInputStream(excelFile);
					HSSFWorkbook workbook = new HSSFWorkbook(fis);
					int sheetNum = 1;
					for (int i = 0; i < sheetNum; i++) {
						HSSFSheet sheet = workbook.getSheetAt(i); // 得到第0+i个工作薄
						// 当前没有工作薄或没有数据
						if (sheet == null || sheet.getLastRowNum() == 0) {
							continue;
						}
						// 遍历一行中的内容
						for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
							Object obj = cls.newInstance();
							HSSFRow row = sheet.getRow(j);// 得到某一行
							if (row == null) {
								continue;
							}

							int cellNum = row.getLastCellNum();
							if (j == 0) {// 不读第一行
								int titleCount = 0;
								for (int k = 0; k < cellNum; k++) {
									Object title = row.getCell(k);
									if (title != null) {
										String titleName = row.getCell(k).toString().trim();
										for (ExcelHeader excelHeader : headers) {
											if (titleName.equals(excelHeader.getTitle())) {
												excelHeader.setOrder(k);
												titleCount++; // 有匹配的表头
											}
											if ("交易日期".equals(titleName)) {
												num = k;
											}
										}
									}
								}
								if (titleCount == 0) { // 没有一个匹配的表头
									return list;
								}
								continue;
							}

							// 标头多少单元格
							try {
								boolean isAdd = false;
								for (int k = 0; k < cellNum; k++) {
									for (ExcelHeader excelHeader : headers) {
										if (k == excelHeader.getOrder()) {
											Field field = obj.getClass().getDeclaredField(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Method methodd = obj.getClass().getMethod(excelHeader.getMethodName(), field.getType());
											String type = map.get(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Object value = row.getCell(k);
											if (value != null && !"".equals(value.toString().trim())) {
												isAdd = true;
												// 时间格式
												if (num != -1) {
													HSSFCell cell = row.getCell(num);
													int cellType = cell.getCellType();
													String value2 = "";
													if (cellType == Cell.CELL_TYPE_NUMERIC) {
														if (HSSFDateUtil.isCellDateFormatted(cell)) {
															// 用于转化为日期格式
															Date d = cell.getDateCellValue();
															DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
															value2 = formater.format(d);
														} else {
															BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
															value2 = bigDecimal.toString();
														}
													} else if (cellType == Cell.CELL_TYPE_STRING) {
														value2 = cell.getStringCellValue();
													}
													methodd.invoke(obj, value2.trim());
												} else {
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
								}
								if (isAdd) {
									list.add(obj);
								}
							} catch (Exception e) {
								throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getImportList07(Class cls, File[] confirm) throws Exception {
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
		if (confirm != null && confirm.length > 0) {
			for (File excelFile : confirm) {
				try {
					FileInputStream fis = new FileInputStream(excelFile);
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					int sheetNum = 1;
					for (int i = 0; i < sheetNum; i++) {
						XSSFSheet sheet = workbook.getSheetAt(i); // 得到第0+i个工作薄
						// 当前没有工作薄或没有数据
						if (sheet == null || sheet.getLastRowNum() == 0) {
							continue;
						}
						// 遍历一行中的内容
						for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
							Object obj = cls.newInstance();
							XSSFRow row = sheet.getRow(j);// 得到某一行
							if (row == null) {
								continue;
							}
							int cellNum = row.getLastCellNum();
							if (j == 0) {// 不读第一行
								int titleCount = 0;
								for (int k = 0; k < cellNum; k++) {
									Object title = row.getCell(k);
									if (title != null) {
										String titleName = row.getCell(k).toString().trim();
										for (ExcelHeader excelHeader : headers) {
											if (titleName.equals(excelHeader.getTitle())) {
												excelHeader.setOrder(k);
												titleCount++; // 有匹配的表头
											}
										}
									}
								}
								if (titleCount == 0) { // 没有一个匹配的表头
									return list;
								}
								continue;
							}
							// 标头多少单元格
							try {
								boolean isAdd = false;
								for (int k = 0; k < cellNum; k++) {
									for (ExcelHeader excelHeader : headers) {
										if (k == excelHeader.getOrder()) {
											Field field = obj.getClass().getDeclaredField(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Method methodd = obj.getClass().getMethod(excelHeader.getMethodName(), field.getType());
											String type = map.get(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Object value = row.getCell(k);
											if (value != null && !"".equals(value.toString().trim())) {
												isAdd = true;
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
								if (isAdd) {
									list.add(obj);
								}
							} catch (Exception e) {
								throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getImportListFormatDate07(Class cls, File[] confirm) throws Exception {
		int num = -1;
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
		if (confirm != null && confirm.length > 0) {
			for (File excelFile : confirm) {
				try {
					FileInputStream fis = new FileInputStream(excelFile);
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					int sheetNum = 1;
					for (int i = 0; i < sheetNum; i++) {
						XSSFSheet sheet = workbook.getSheetAt(i); // 得到第0+i个工作薄
						// 当前没有工作薄或没有数据
						if (sheet == null || sheet.getLastRowNum() == 0) {
							continue;
						}

						// 遍历一行中的内容
						for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
							Object obj = cls.newInstance();
							XSSFRow row = sheet.getRow(j);// 得到某一行
							if (row == null) {
								continue;
							}
							int cellNum = row.getLastCellNum();
							if (j == 0) {// 不读第一行
								int titleCount = 0;
								for (int k = 0; k < cellNum; k++) {
									Object title = row.getCell(k);
									if (title != null) {
										String titleName = row.getCell(k).toString().trim();
										for (ExcelHeader excelHeader : headers) {
											if (titleName.equals(excelHeader.getTitle())) {
												excelHeader.setOrder(k);
												titleCount++; // 有匹配的表头
											}
											if ("交易日期".equals(titleName)) {
												num = k;
											}
										}
									}
								}
								if (titleCount == 0) { // 没有一个匹配的表头
									return list;
								}
								continue;
							}

							// 标头多少单元格
							try {
								boolean isAdd = false;
								for (int k = 0; k < cellNum; k++) {
									for (ExcelHeader excelHeader : headers) {
										if (k == excelHeader.getOrder()) {
											Field field = obj.getClass().getDeclaredField(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Method methodd = obj.getClass().getMethod(excelHeader.getMethodName(), field.getType());
											String type = map.get(initlet(excelHeader.getMethodName().replace("set", "").toString()));
											Object value = row.getCell(k);
											if (value != null && !"".equals(value.toString().trim())) {
												isAdd = true;
												// 时间格式
												if (num != -1 && num == k) {
													XSSFCell cell = row.getCell(num);
													int cellType = cell.getCellType();
													String value2 = "";
													if (cellType == Cell.CELL_TYPE_NUMERIC) {
														if (HSSFDateUtil.isCellDateFormatted(cell)) {
															// 用于转化为日期格式
															Date d = cell.getDateCellValue();
															DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
															value2 = formater.format(d);
														} else {
															BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
															value2 = bigDecimal.toString();
														}
													} else if (cellType == Cell.CELL_TYPE_STRING) {
														value2 = cell.getStringCellValue();
													}

													methodd.invoke(obj, value2.trim());
												} else {
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
								}
								if (isAdd) {
									list.add(obj);
								}
							} catch (Exception e) {
								throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 生成文件头
	 * 
	 * @return
	 */
	public String getExcelHeader() {
		StringBuffer header = new StringBuffer();
		// 头部
		header.append("<?xml version=\"1.0\"?>\r\n");
		header.append("<?mso-application progid=\"Excel.Sheet\"?>\r\n");
		header.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\r\n\t");
		header.append("xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n\t");
		header.append("xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\r\n\t");
		header.append("xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"\r\n\t");
		header.append("xmlns:html=\"http://www.w3.org/TR/REC-html40\">\r\n\t");
		header.append("<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\r\n\t\t");
		header.append("<Author>Sanweidu</Author>\r\n\t\t");
		header.append("<LastAuthor>Sanweidu</LastAuthor>\r\n\t\t");
		header.append("<Created>" + DatePattern.NORM_DATETIME_FORMAT.format(new Date()) + "</Created>\r\n\t\t");
		header.append("<Version>16.00</Version>\r\n\t");
		header.append("</DocumentProperties>\r\n\t");
		header.append("<OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\r\n\t\t");
		header.append("<AllowPNG/>\r\n\t");
		header.append("</OfficeDocumentSettings>\r\n\t");
		header.append("<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\r\n\t\t");
		header.append("<WindowHeight>0</WindowHeight>\r\n\t\t");
		header.append("<WindowWidth>0</WindowWidth>\r\n\t\t");
		header.append("<WindowTopX>0</WindowTopX>\r\n\t\t");
		header.append("<WindowTopY>0</WindowTopY>\r\n\t\t");
		header.append("<ProtectStructure>False</ProtectStructure>\r\n\t\t");
		header.append("<ProtectWindows>False</ProtectWindows>\r\n\t");
		header.append("</ExcelWorkbook>\r\n\t");
		// 样式
		header.append("<Styles>\r\n\t\t");
		header.append("<Style ss:ID=\"Default\" ss:Name=\"Normal\">\r\n\t\t\t");
		header.append("<Alignment ss:Vertical=\"Center\"/>\r\n\t\t\t");
		header.append("<Borders/>\r\n\t\t\t");
		header.append("<Font ss:FontName=\"等线\" x:CharSet=\"134\" ss:Size=\"11\" ss:Color=\"#000000\"/>\r\n\t\t\t");
		header.append("<Interior/>\r\n\t\t\t");
		header.append("<NumberFormat/>\r\n\t\t\t");
		header.append("<Protection/>\r\n\t\t");
		header.append("</Style>\r\n\t\t");
		header.append("<Style ss:ID=\"s16\">\r\n\t\t\t");
		header.append("<Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>\r\n\t\t\t");
		header.append("<Font ss:FontName=\"宋体\" x:CharSet=\"134\"/>\r\n\t\t\t");
		header.append("<NumberFormat ss:Format=\"@\"/>\r\n\t\t");
		header.append("</Style>\r\n\t");
		header.append("</Styles>\r\n");
		return header.toString();
	}

	/**
	 * 生成文件主体
	 * 
	 * @param list
	 * @param clz
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getExcelBody(List list, Class clz, Map<String, String> datas) throws Exception {
		List<ExcelHeader> headers = getHeaderList(clz);
		StringBuffer body = new StringBuffer();

		int totalCount = list.size();
		// 总的页数
		int pageCount = 0;
		// 每页显示的总数
		int endNum = 60000;
		/* 计算出总共能分成多少页 */
		if (totalCount % endNum > 0) {// 数据总数和每页显示的总数不能整除的情况
			pageCount = totalCount / endNum + 1;
		} else {// 数据总数和每页显示的总数能整除的情况
			pageCount = totalCount / endNum;
		}
		if (pageCount > 0) {
			for (int k = 0; k < pageCount; k++) {
				List objs = null;
				int startNum = k + 1; // 当前页码
				if (startNum == 1) {// 当前页数为第一页
					if (totalCount <= endNum) {// 数据总数小于每页显示的数据条数
						objs = list;
					} else {// 截止到总的数据条数(当前数据不足一页，按一页显示)，这样才不会出现数组越界异常
						objs = list.subList(0, endNum);
					}
				} else {
					// 截取起始下标
					int fromIndex = (startNum - 1) * endNum;
					// 截取截止下标
					int toIndex = startNum * endNum;
					/* 计算截取截止下标 */
					if ((totalCount - toIndex) % endNum >= 0) {
						toIndex = startNum * endNum;
					} else {
						toIndex = (startNum - 1) * endNum + (totalCount % endNum);
					}
					if (totalCount >= toIndex) {
						objs = list.subList(fromIndex, toIndex);
					}
				}
				if (pageCount > 1) {
					body.append("\t<Worksheet ss:Name=\"" + datas.get("title") + startNum + "\">\r\n");
				} else {
					body.append("\t<Worksheet ss:Name=\"" + datas.get("title") + "\">\r\n");
				}
				body.append("\t\t<Table ss:ExpandedColumnCount=\"" + headers.size() + "\" ss:ExpandedRowCount=\"" + (objs.size() + 1) + "\" x:FullColumns=\"1\"\r\n");
				body.append("\t\t\tx:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">\r\n");
				body.append("\t\t\t<Row>\r\n");
				for (int j = 0; j < headers.size(); j++) {
					ExcelHeader eh = headers.get(j);
					body.append("\t\t\t\t<Cell ss:StyleID=\"s16\"><Data ss:Type=\"String\">" + eh.getTitle() + "</Data></Cell>\r\n");
				}
				body.append("\t\t\t</Row>\r\n");
				for (int i = 0; i < objs.size(); i++) {
					body.append("\t\t\t<Row>\r\n");
					Object obj = objs.get(i);
					String name = "";
					for (int j = 0; j < headers.size(); j++) {
						ExcelHeader eh = headers.get(j);
						String mn = eh.getMethodName();
						Method m = clz.getMethod(mn);
						Object rel = m.invoke(obj);
						if (rel == null) {
							name = "暂无";
						} else {
							name = rel.toString().trim();
						}
						body.append("\t\t\t\t<Cell ss:StyleID=\"s16\"><Data ss:Type=\"String\">" + name + "</Data></Cell>\r\n");
					}
					body.append("\t\t\t</Row>\r\n");
				}
				body.append("\t\t</Table>\r\n");
				body.append("\t</Worksheet>\r\n");
			}
		} else {
			body.append("\t<Worksheet ss:Name=\"" + datas.get("title") + "\">\r\n");
			body.append("\t\t<Table ss:ExpandedColumnCount=\"" + headers.size() + "\" ss:ExpandedRowCount=\"1\" x:FullColumns=\"1\"\r\n");
			body.append("\t\t\tx:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">\r\n");
			body.append("\t\t\t<Row>\r\n");
			for (int j = 0; j < headers.size(); j++) {
				ExcelHeader eh = headers.get(j);
				body.append("\t\t\t\t<Cell ss:StyleID=\"s16\"><Data ss:Type=\"String\">" + eh.getTitle() + "</Data></Cell>\r\n");
			}
			body.append("\t\t\t</Row>\r\n");
			body.append("\t\t</Table>\r\n");
			body.append("\t</Worksheet>\r\n");
		}
		return body.toString();
	}

	/**
	 * 导出Xml形式Excel，适用于大数据导出
	 * 
	 * @param objs
	 * @param clz
	 * @param datas
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String exportXMLExcel(List objs, Class clz, Map<String, String> datas, Integer page) throws Exception {
		String fileName = getExcelFileName(clz, page, "xml");
		File file = new File(fileName);
		PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
		writer.print(getExcelHeader());
		writer.print(getExcelBody(objs, clz, datas));
		// 写入excel文件尾部
		writer.print("</Workbook>");
		writer.flush();
		writer.close();
		if (file.exists()) {
			return fileName;
		} else {
			return null;
		}
	}

	/**
	 * 导出Excel,单个Sheet
	 * 
	 * @param objs  数据列表
	 * @param clz
	 * @param datas
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String exportObjXSSFSingleExcel(List objs, Class clz, Map<String, String> datas, Integer page) {
		try {
			Workbook workbook = POIExcelUtil.getInstance().getWorkbookSingleSheet(objs, clz, datas);
			String fileName = getExcelFileName(clz, page, "xlsx");
			File file = new File(fileName);
			OutputStream buffer = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(buffer);
			buffer.close();
			log.info("export success!");
			return file.exists() ? fileName : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
	}

	/**
	 * excel导出多个Sheet
	 * 
	 * @param listsize个Sheet
	 * @param clz
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String exportObjXSSFMultipleExcel(List<List> list, Class clz, Map<String, String> datas) {
		try {
			Workbook workbook = POIExcelUtil.getInstance().getWorkbookMultipleSheet(list, clz, datas);
			String fileName = getExcelFileName(clz, null, "xlsx");
			File file = new File(fileName);
			OutputStream buffer = new BufferedOutputStream(new FileOutputStream(file));
			workbook.write(buffer);
			buffer.close();
			log.info("export mulitple excel success!");
			return file.exists() ? fileName : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
	}

	/**
	 * 导入报表
	 * 
	 * @param cls
	 * @param confirm
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public List getImportList(Class cls, File[] confirm) {
		for (File excelFile : confirm) {
			try {
				FileInputStream fis = new FileInputStream(excelFile);
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				return getImportList03(cls, confirm); // Excel2003
			} catch (Exception e) {
				try {
					FileInputStream fis = new FileInputStream(excelFile);
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					return getImportList07(cls, confirm); // Excel2007
				} catch (Exception e2) {
					throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
				}
			}
		}
		return null;
	}

	/**
	 * 导入报表
	 * 
	 * @param cls
	 * @param file
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public List getImportList(Class cls, File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			return getImportList03(cls, new File[] { file }); // Excel2003
		} catch (Exception e) {
			try {
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				return getImportList07(cls, new File[] { file }); // Excel2007
			} catch (Exception e2) {
				throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
			}
		}
	}

	/**
	 * 导入报表
	 * 
	 * @param cls
	 * @param file
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public List getImportListFormatDate(Class cls, File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			return getImportListFormatDate03(cls, new File[] { file }); // Excel2003
		} catch (Exception e) {
			try {
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				return getImportListFormatDate07(cls, new File[] { file }); // Excel2007
			} catch (Exception e2) {
				throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
			}
		}
	}

}
