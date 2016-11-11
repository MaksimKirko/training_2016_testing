package com.github.maximkirko.testing.services;

import java.util.List;

public interface IEntityService<T, PK> {
	
	T get(PK id);

	T getWithQuestions(PK id);

	List getAll();

	PK save(T entity);

	void saveAll(List<T> entities);

	void delete(PK id);
}
