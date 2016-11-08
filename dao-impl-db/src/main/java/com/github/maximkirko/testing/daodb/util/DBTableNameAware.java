package com.github.maximkirko.testing.daodb.util;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

public class DBTableNameAware {
	public static String getTableNameByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(DBTable.class).name();
	}

	public static String getTableNameByClass(Class<?> entityClass1, Class<?> entityClass2) {
		String name = String.format("%s_2_%s", entityClass1.getAnnotation(DBTable.class).name(),
				entityClass2.getAnnotation(DBTable.class).name());
		return name;
	}
}