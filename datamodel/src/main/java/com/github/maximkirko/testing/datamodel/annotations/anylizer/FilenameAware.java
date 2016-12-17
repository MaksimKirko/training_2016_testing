package com.github.maximkirko.testing.datamodel.annotations.anylizer;

import com.github.maximkirko.testing.datamodel.annotations.Filename;

public class FilenameAware {
	
	public static String getFilenameByClass(Class<?> entityClass) {
		return entityClass.getAnnotation(Filename.class).value();
	}
}
