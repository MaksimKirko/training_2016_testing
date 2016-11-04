package com.github.maximkirko.testing.daodb;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public interface GenericDao<T extends AbstractModel> {
	T get(Long id);

    void insert(T entity);

    void update(T entity);

    void delete(Long id);

    List<T> getAll();
}