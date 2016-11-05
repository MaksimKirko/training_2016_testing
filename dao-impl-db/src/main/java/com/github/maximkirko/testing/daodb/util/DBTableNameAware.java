package com.github.maximkirko.testing.daodb.util;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

public class DBTableNameAware {
	public static String getTableNameByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(DBTable.class).name();
	}
}