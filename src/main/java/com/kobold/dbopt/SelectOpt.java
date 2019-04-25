package com.kobold.dbopt;

import com.kobold.context.IDbFactory;
import com.kobold.qkutils.ExecuteUtils;
import com.kobold.qkutils.NamedParameterStatement;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectOpt<T> extends DbResult<T> {
	private IDbFactory dbFactory;

	public SelectOpt(IDbFactory dbFactory) {
		this.dbFactory = dbFactory;
	}

	/**
	 * TODO 根据表名和条件获取数据
	 *
	 * @param clazz
	 * @return
	 */
	public List<T> list(Class<?> clazz, ParameterMetaData data) {
		return null;
	}

	public List<T> findAll(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		Connection connection = dbFactory.getConnection();
		NamedParameterStatement preparedStatement = null;
		try {
			preparedStatement = new NamedParameterStatement(connection, sql);
			for (String key : objectMap.keySet()) {
				preparedStatement.setObject(key, objectMap.get(key));
			}
			ResultSet set = preparedStatement.executeQuery();
			return ExecuteUtils.ConvertToObjectList(set, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public T findFirst(String sql, Map<String, Object> objectMap, Class<T> tClass) {
		return findAll(sql, objectMap, tClass).stream().findFirst().get();
	}
}
