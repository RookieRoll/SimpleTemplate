package com.kobold.QKUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertiesUtil {
	public static <T> T loadProperty(String filePath, Class<?> clazz) {
		Field[] fields = clazz.getFields();
		Properties properties = getProperties(filePath);
		T t = null;
		try {
			t = (T) clazz.getConstructor(null).newInstance(null);

			for (Field field : fields) {
				field.setAccessible(true);
				field.set(t, properties.get(field.getName()));
			}
		} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	private static Properties getProperties(String filePath) {
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(filePath);//加载Java项目根路径下的配置文件
			properties.load(input);// 加载属性文件

		} catch (IOException io) {

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;

	}
}
