package com.github.maximkirko.testing.daodb.util;

public class Utils {
	public static String getTableNameByClass(Class entityClass) {
		switch(entityClass.getSimpleName().toLowerCase()) {
		case "quiz": 
			return "quiz";
		}
		return null;
	}
}