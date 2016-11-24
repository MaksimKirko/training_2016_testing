package com.github.maximkirko.testing.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T, PK> {

	T get(PK id);

	List<T> getAll();

	@Transactional
	PK save(T entity);

	@Transactional
	List<PK> saveAll(List<T> entities);

	@Transactional
	void delete(PK id);

}
