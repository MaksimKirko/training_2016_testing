package com.github.maximkirko.testing.daodb;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public interface IGenericManyToManyDao<T, PK1, PK2> {
	
	void insert(AbstractModel entity);
	
	void deleteByFirstId(PK1 id);

	void deleteBySecondId(PK2 id);
}
