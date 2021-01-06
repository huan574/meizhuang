package com.meizhuang.utils.excel;

public class ExcelHeader implements Comparable<ExcelHeader> {

	private String title;
	private int order;
	private String methodName;

	public ExcelHeader() {
	}

	public ExcelHeader(String title, int order, String methodName) {
		this.title = title;
		this.order = order;
		this.methodName = methodName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/***
	 * 1：顺着排序，-1倒序 0：表示一样
	 */
	public int compareTo(ExcelHeader o) {
		return order > o.order ? 1 : (order < o.order ? -1 : 0);
	}

	@Override
	public String toString() {
		return "ExcelHeader [methodName=" + methodName + ", order=" + order + ", title=" + title + "]";
	}

}
