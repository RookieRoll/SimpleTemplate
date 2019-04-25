package com.kobold.dbopt;

import java.sql.ResultSet;
import java.util.List;

public class DbResult<T> {
	private ResultSet resultSet;
	private List<T> resultList;

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}
