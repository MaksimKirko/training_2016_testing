package com.github.maximkirko.testing.daodb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public interface IGenericDao<T extends AbstractModel, PK extends Serializable> {

	Map entityToMap(T entity);
	
	T get(PK id);

	PK insert(T entity);

	void update(T entity);

	void delete(PK id);

	List<T> getAll();
}