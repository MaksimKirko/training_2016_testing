package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;

public interface IGenericService<T, PK> {

	T get(PK id);

	List<T> getAll();

	PK save(T entity);

	void saveAll(List<T> entity);

	void delete(PK id);
	
}
