package com.github.maximkirko.testing.daodb;

import java.util.List;

public interface GenericDao<T> {
	T get(Long id);

    void insert(T entity);

    void update(T entity);

    void delete(Long id);

    List<T> getAll();
}