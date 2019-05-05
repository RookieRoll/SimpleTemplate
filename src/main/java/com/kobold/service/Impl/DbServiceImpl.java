package com.kobold.service.Impl;

import com.kobold.codegenerates.FreeMarkerTemplateUtils;
import com.kobold.consts.DBConst;
import com.kobold.context.DefaultDbApplicationContext;
import com.kobold.context.IDbFactory;
import com.kobold.models.FieldInfo;
import com.kobold.models.MethodInfo;
import com.kobold.models.ModelInfo;
import com.kobold.qkutils.DataBaseUtils;
import com.kobold.consts.MethodConst;
import com.mysql.cj.util.StringUtils;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DbServiceImpl {
	private final String baseObjName = "QkBaseDto";
	private final String dbDtoTemplate = "Dto.ftl";

	public void handleTable() {
		Connection connection = null;
		ResultSet tableRs = null;
		try {
			IDbFactory context = new DefaultDbApplicationContext();
			connection = context.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			tableRs = metaData.getTables(context.getDbName(), null, "%", new String[]{DBConst.TABLE});
			List<ModelInfo> modelInfos = new ArrayList<>();
			while (tableRs.next()) {
				String tableName = tableRs.getString(DBConst.TABLE_NAME);
				String remark = tableRs.getString(DBConst.REMARKS);
				ModelInfo modelInfo = new ModelInfo();
				modelInfo.setClassName(convertToJavaName(tableName, true));
				modelInfo.setComment(remark);
				modelInfo.setTableName(tableName);
				List<FieldInfo> fieldInfos = handleTableField(metaData.getColumns(context.getDbName(), null, tableName, "%"));
				modelInfo.setFieldInfoList(fieldInfos);
				modelInfo.setMethodInfoList(handleMethod(modelInfo));
				modelInfos.add(modelInfo);
			}
			outputFiles(modelInfos);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		} finally {
			try {
				tableRs.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
			}
		}

	}

	public List<MethodInfo> handleMethod(ModelInfo modelInfo) {
		List<MethodInfo> methodInfos = new ArrayList<>();
		for (FieldInfo fieldInfo : modelInfo.getFieldInfoList()) {
			MethodInfo methodInfo = handleGetMethod(fieldInfo);
			methodInfos.add(methodInfo);
			methodInfo = handleSetMethod(fieldInfo);
			methodInfos.add(methodInfo);
		}
		return methodInfos;

	}


	public MethodInfo handleGetMethod(FieldInfo fieldInfo) {
		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setStatic(false);
		methodInfo.setMethodTypeName(fieldInfo.getFieldTypeName());
		methodInfo.setAccessibility(MethodConst.PUBLIC);
		methodInfo.setMethodName(MethodConst.GET + convertToJavaName(fieldInfo.getFieldName(), true));
		methodInfo.setMethodBody(methodInfo.handleBaseGetMethodBody(fieldInfo.getFieldName()));

		return methodInfo;
	}

	public MethodInfo handleSetMethod(FieldInfo fieldInfo) {
		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setStatic(false);
		methodInfo.setMethodTypeName(MethodConst.VOID);
		methodInfo.setAccessibility(MethodConst.PUBLIC);
		methodInfo.setStatic(false);
		methodInfo.setMethodName(MethodConst.SET + convertToJavaName(fieldInfo.getFieldName(), true));
		//参数处理
		methodInfo.addArguments(fieldInfo.getFieldType(), fieldInfo.getFieldName());
		//处理方法体
		methodInfo.setMethodBody(methodInfo.handleBaseSetMethodBody(fieldInfo.getFieldName()));
		return methodInfo;
	}

	public void outputFiles(List<ModelInfo> modelInfos) {
		try {
			File file = new File(baseObjName + ".java");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String packageName = modelInfos.get(0).getPackageName();
			FreeMarkerTemplateUtils.processTemplate(baseObjName + ".ftl", packageName, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {

		}
		modelInfos.stream().forEach(modelInfo -> {
			File file = new File(modelInfo.getClassName() + ".java");
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				FreeMarkerTemplateUtils.processTemplate(dbDtoTemplate, modelInfo, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		});
	}

	private List<FieldInfo> handleTableField(ResultSet column) throws SQLException, ClassNotFoundException {
		List<FieldInfo> fieldInfos = new ArrayList<>();
		while (column.next()) {
			FieldInfo info = new FieldInfo();
			info.setDbFieldName(column.getString(DBConst.COLUMN_NAME));
			JDBCType jdbcType = JDBCType.valueOf(column.getInt(DBConst.DATA_TYPE));
			info.setDbType(jdbcType.toString());
			info.setComment(column.getString(DBConst.REMARKS));
			info.setFieldName(convertToFieldName(info.getDbFieldName()));
			info.setFieldType(DataBaseUtils.getJavaClassTypeByDbType(jdbcType));
			info.setFieldTypeName(info.getFieldType().getSimpleName());
			fieldInfos.add(info);
		}
		return fieldInfos;
	}

	/**
	 * 将表名转换为实体名
	 * 表名多个单词以下划线隔开
	 * 实体名使用驼峰规则
	 */
	private String convertToJavaName(String tableName, boolean isUpper) {
		String[] words = splitString(tableName.toLowerCase(), "_");
		StringBuilder entityName = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			entityName.append(toUppercaseForFirstLetter(words[i], isUpper));
		}
		return entityName.toString();
	}

	/**
	 * @param columnName
	 * @return
	 */

	public static String convertToFieldName(String columnName) {
		String[] words = splitString(columnName.toLowerCase(), "_");
		StringBuilder entityName = new StringBuilder();
		for (int i = 1; i < words.length; i++) {
			entityName.append(toUppercaseForFirstLetter(words[i], true));
		}
		words[0] = toUppercaseForFirstLetter(words[0], false);
		entityName.insert(0, words[0]);
		return entityName.toString();
	}

	public static String toUppercaseForFirstLetter(String str, boolean isUpper) {
		if (str.isEmpty())
			return str;
		char[] chars = str.toCharArray();
		chars[0] = isUpper ? Character.toUpperCase(chars[0]) : Character.toLowerCase(chars[0]);
		return new String(chars);
	}

	/**
	 * 使用StringTokenizer分割字符串，不包含界符
	 */
	public static String[] splitString(String str, String delim) {
		if (StringUtils.isEmptyOrWhitespaceOnly(str))
			return null;
		StringTokenizer tokens = new StringTokenizer(str, delim);
		String[] rs = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreTokens())
			rs[i++] = tokens.nextToken();

		return rs;
	}
}
