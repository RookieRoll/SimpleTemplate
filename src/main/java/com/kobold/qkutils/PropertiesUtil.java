package com.kobold.qkutils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {
	public static <T> T loadProperty(String filePath, Class<?> clazz) {
		List<Field> fields=new ArrayList<>();
		Class<?> cla=clazz;
		while (cla!=null&&cla!=Object.class){
			fields.addAll(Arrays.asList(cla.getDeclaredFields()));
			cla=cla.getSuperclass();
		}
		Properties properties = getProperties(filePath);
		T t = null;
		try {
			t = (T) clazz.getConstructor(null).newInstance(null);

			for (Field field : fields) {
				field.setAccessible(true);
				if(properties.containsKey(field.getName().toLowerCase()))
					field.set(t, properties.get(field.getName().toLowerCase()));
			}
		} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	private static Properties getProperties(String filePath) {
		Properties properties = new Properties();
		InputStream resource= ResourceUtil.getResource(filePath);
		try {
			properties.load(resource);// 加载属性文件
		} catch (IOException io) {
			System.out.println(io.getMessage());

		} finally {
			if (resource != null) {
				try {
					resource.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;

	}
}
