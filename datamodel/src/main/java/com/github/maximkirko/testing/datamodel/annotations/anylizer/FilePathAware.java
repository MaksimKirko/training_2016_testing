package com.github.maximkirko.testing.datamodel.annotations.anylizer;

import com.github.maximkirko.testing.datamodel.annotations.FilePath;

public class FilePathAware {
	
	public static String getFilePathByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(FilePath.class).name();
	}
}
