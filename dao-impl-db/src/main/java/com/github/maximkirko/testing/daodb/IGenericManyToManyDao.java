package com.github.maximkirko.testing.daodb;

import java.util.List;
import java.util.Map;

public interface IGenericManyToManyDao<T1, T2, PK1, PK2> {
	
	List<Map> entityToMap(T1 entity);
	
	void insert(T1 entity);
	
	void deleteByFirstId(PK1 id);

	void deleteBySecondId(PK2 id);
}
