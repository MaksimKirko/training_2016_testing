package com.github.maximkirko.testing.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IGenericService<T, PK> {

	T get(PK id);

	List<T> getAll();

	PK save(T entity);

	List<PK> saveAll(List<T> entities);

	void delete(PK id);

}
