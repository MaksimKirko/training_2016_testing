package com.github.maximkirko.testing.daodb.util;

import java.lang.reflect.Field;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public class GenericTypeInfo {
	
	public static String getFields(Class<?> entityClass, Object entity) {

		String types = "";

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				if(field.getType().getSuperclass().equals(AbstractModel.class)) {
					continue;
				}
				if (field.get(entity) != null) {
					types += String.format("%s", field.getName());
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return types.substring(0, types.length() - 2);
	}

	public static String getFieldsValues(Class<?> entityClass, Object entity) {

		String values = "";

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				if(field.getType().getSuperclass().equals(AbstractModel.class)) {
					continue;
				}
				if (field.get(entity) != null) {
					values += String.format("'%s', ", field.get(entity));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return values.substring(0, values.length() - 2);
	}
	
	public static String getValuesForUpdate(Class<?> entityClass, Object entity) {
		
		String values = "";
		
		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				values += String.format("%s='%s', ", field.getName(), field.get(entity));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return values = values.substring(0, values.length() - 2);
	}
}
