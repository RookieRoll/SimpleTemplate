package com.kobold.QKUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseUtils {
	private static Map<JDBCType, Class<?>> javaTypes = new HashMap<JDBCType, Class<?>>();

	static {
		javaTypes.put(JDBCType.CHAR, String.class);
		javaTypes.put(JDBCType.VARCHAR, String.class);
		javaTypes.put(JDBCType.LONGNVARCHAR, String.class);
		javaTypes.put(JDBCType.LONGVARCHAR, String.class);

		javaTypes.put(JDBCType.NUMERIC, BigDecimal.class);
		javaTypes.put(JDBCType.DECIMAL, BigDecimal.class);

		javaTypes.put(JDBCType.BIT, Boolean.class);
		javaTypes.put(JDBCType.BOOLEAN, Boolean.class);

		javaTypes.put(JDBCType.TINYINT, Byte.class);

		javaTypes.put(JDBCType.SMALLINT, Short.class);

		javaTypes.put(JDBCType.INTEGER, Integer.class);

		javaTypes.put(JDBCType.BIGINT, Long.class);

		javaTypes.put(JDBCType.REAL, Float.class);

		javaTypes.put(JDBCType.FLOAT, Double.class);
		javaTypes.put(JDBCType.DOUBLE, Double.class);

		javaTypes.put(JDBCType.BINARY, byte[].class);
		javaTypes.put(JDBCType.VARBINARY, byte[].class);
		javaTypes.put(JDBCType.LONGVARBINARY, byte[].class);
		javaTypes.put(JDBCType.BLOB, byte[].class);

		javaTypes.put(JDBCType.DATE, Date.class);
		javaTypes.put(JDBCType.TIME, Date.class);
		javaTypes.put(JDBCType.TIMESTAMP, Date.class);

		javaTypes.put(JDBCType.CLOB, Clob.class);

		javaTypes.put(JDBCType.ARRAY, Array.class);

		javaTypes.put(JDBCType.DISTINCT, null);

		javaTypes.put(JDBCType.STRUCT, Struct.class);

		javaTypes.put(JDBCType.REF, Ref.class);

		javaTypes.put(JDBCType.DATALINK, URL.class);
	}

	public static Class<?> getJavaClassTypeByDbType(JDBCType type){
		return javaTypes.get(type);
	}
}
