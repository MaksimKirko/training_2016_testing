package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.Map;

public interface EntityToMap<T> {
	
	Map<String, Object> convert(T entity);
}
