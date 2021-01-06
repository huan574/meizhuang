package com.meizhuang.entity.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public enum SystemOperationType {

	/**
	 * 操作类型
	 */
	UNKNOWN("unknown", "未知"), // 未知
	SELECT("select", "查询"), // 查询
	INSERT("insert", "添加"), // 添加
	UPDATE("update", "修改"), // 修改
	DELETE("delete", "删除"); // 删除

	private String value;
	private String desc;

	SystemOperationType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDescByValue(String value) {
		if (StringUtils.isNotEmpty(value)) {
			SystemOperationType[] operationTypes = SystemOperationType.values();
			for (SystemOperationType operationType : operationTypes) {
				if (operationType.getValue().equals(value)) {
					return operationType.getDesc();
				}
			}
		}
		return null;
	}

	public static List<SystemOperationType.OperationType> getOperationTypes() {
		List<SystemOperationType.OperationType> list = new ArrayList<SystemOperationType.OperationType>();
		SystemOperationType[] enums = SystemOperationType.values();
		for (SystemOperationType operationTypeEnum : enums) {
			list.add(operationTypeEnum.new OperationType(operationTypeEnum.getValue(), operationTypeEnum.getDesc()));
		}
		return list;
	}

	@ApiModel(description = "操作类型返回参数")
	public class OperationType {

		@ApiModelProperty(value = "操作类型")
		private String type;
		@ApiModelProperty(value = "操作描述")
		private String desc;

		private OperationType(String type, String desc) {
			this.type = type;
			this.desc = desc;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}
}
