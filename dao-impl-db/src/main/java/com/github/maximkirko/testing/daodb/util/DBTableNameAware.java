package com.github.maximkirko.testing.daodb.util;

import java.util.Arrays;
import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

public class DBTableNameAware {

	public static String getTableNameByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(DBTable.class).name();
	}

	public static List<String> getManyToManyTableFields(String tableName) {

		String[] arr = tableName.split("_2_");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = String.format("%s_id", arr[i]);
		}

		List<String> fields = Arrays.asList(arr);

		return fields;
	}
}