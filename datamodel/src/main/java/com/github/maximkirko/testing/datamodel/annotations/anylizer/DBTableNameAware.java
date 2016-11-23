package com.github.maximkirko.testing.datamodel.annotations.anylizer;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

public class DBTableNameAware {

	public static String getTableNameByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(DBTable.class).value();
	}
	
}