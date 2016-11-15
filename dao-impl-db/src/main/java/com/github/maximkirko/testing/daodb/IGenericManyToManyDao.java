package com.github.maximkirko.testing.daodb;

import java.util.List;
import java.util.Map;

import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;

public interface IGenericManyToManyDao<T, PK1, PK2> {

	List<T> getByFirstId(PK1 id);

	List<T> getBySecondId(PK2 id);

	void insert(T entity);

	void deleteByFirstId(PK1 id);

	void deleteBySecondId(PK2 id);
}
