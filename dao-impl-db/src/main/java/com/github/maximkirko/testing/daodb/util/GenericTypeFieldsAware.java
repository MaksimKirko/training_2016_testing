package com.github.maximkirko.testing.daodb.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public class GenericTypeFieldsAware {

	public static List<String> getManyToManyFieldsNames(Class<?> entityClass) {

		List<String> types = new ArrayList<String>();

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);
			types.add(String.format("%s_id", field.getName()));
		}

		return types;
	}

	public static Map getManyToManyFieldsMap(Class<?> entityClass, Object entity) {

		Map<String, Object> fields = new HashMap<String, Object>();

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				fields.put(String.format("%s_id", field.getName()), ((AbstractModel) field.get(entity)).getId());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fields;
	}

	
	/*
	 * TODO: refactor to use the Map of endtity fields
	 */
	public static String getStringForUpdate(Class<?> entityClass, Object entity) {

		String values = "";

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {

				if (field.getType().getSimpleName().toString().equals("List")) {

					continue;

				}

				if (field.getType().getSuperclass() != null) {

					if (field.getType().getSuperclass().equals(AbstractModel.class)) {

						values += String.format("%s_id='%s', ", field.getName(),
								((AbstractModel) field.get(entity)).getId());
						continue;

					}

				}
				
				if (field.getType().getSimpleName().equals("String")) {

					values += String.format("%s='%s', ", field.getName(), field.get(entity));

				} else {

					values += String.format("%s=%s, ", field.getName(), field.get(entity));

				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return values = values.substring(0, values.length() - 2);
	}
}
