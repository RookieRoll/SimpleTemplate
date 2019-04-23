package com.kobold.models;

public class FieldInfo {
	/**
	 * 数据库字段
	 */
	private String dbFieldName;
	/**
	 * 实体字段
	 */
	private String fieldName;
	/**
	 * 数据库类型
	 */
	private String dbType;
	/**
	 * 实体类型
	 */
	private Class<?> fieldType;

	private String fieldTypeName;
	/**
	 * 数据库注释
	 * */
	private String comment;


	public FieldInfo() {

	}

	public String getDbFieldName() {
		return dbFieldName;
	}

	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public Class<?> getFieldType() {
		return fieldType;
	}

	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFieldTypeName() {
		return fieldTypeName;
	}

	public void setFieldTypeName(String fieldTypeName) {
		this.fieldTypeName = fieldTypeName;
	}
}
