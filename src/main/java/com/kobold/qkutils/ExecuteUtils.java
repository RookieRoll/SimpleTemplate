package com.kobold.qkutils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExecuteUtils {
	public static <T> List<T> ConvertToObjectList(ResultSet rs, Class<T> tClass) {
		Class clazz =tClass;
		var paramList = clazz.getDeclaredFields();
		List<T> list = new ArrayList<>();
		try {
			while(rs.next()){
				T obj = (T) clazz.getDeclaredConstructor().newInstance();
				for (Field field : paramList) {
					field.setAccessible(true);
					Object value=(rs.getObject(field.getName()));
					field.set(obj,value);
				}
				list.add(obj);
			}
			rs.close();
		} catch (Exception e) {
			throw  new RuntimeException(e);
		}

		return list;
	}

	private String getInsertSql(Class clazz) {
		String tableName = clazz.getSimpleName();
		Field[] fields = clazz.getDeclaredFields();
		var sql = "Insert into " + tableName + " ( ";
		sql += Arrays.stream(fields).map((m) -> m.getName()).collect(Collectors.joining(","));
		sql += ") values(";
		sql += Arrays.stream(fields).map((m) -> ":" + m.getName()).collect(Collectors.joining(","));
		sql += ")";
		return sql;
	}
}
