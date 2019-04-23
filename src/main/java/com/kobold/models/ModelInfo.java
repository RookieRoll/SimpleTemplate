package com.kobold.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelInfo implements Serializable {
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 表明
	 */
	private String tableName;
	/**
	 * 表注释
	 */
	private String comment;
	/**
	 * 字段列
	 */
	private List<FieldInfo> fieldInfoList=new ArrayList<>();

	private List<MethodInfo> methodInfoList=new ArrayList<>();

	public ModelInfo(){

	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<FieldInfo> getFieldInfoList() {
		return fieldInfoList;
	}

	public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
		this.fieldInfoList = fieldInfoList;
	}

	public List<MethodInfo> getMethodInfoList() {
		return methodInfoList;
	}

	public void setMethodInfoList(List<MethodInfo> methodInfoList) {
		this.methodInfoList = methodInfoList;
	}
}
