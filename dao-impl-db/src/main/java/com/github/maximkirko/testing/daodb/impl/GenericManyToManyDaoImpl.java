package com.github.maximkirko.testing.daodb.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGenericManyToManyDao;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;

@Repository
public class GenericManyToManyDaoImpl<T1, T2, PK1, PK2> implements IGenericManyToManyDao<T1, T2, PK1, PK2> {

	@Inject
	protected JdbcTemplate jdbcTemplate;

	protected Class<T1> entityClass1;
	
	protected Class<T2> entityClass2;

	protected String tableName;

	public GenericManyToManyDaoImpl() {

	}

	public GenericManyToManyDaoImpl(Class<T1> entityClass1, Class<T2> entityClass2) {
		this.entityClass1 = entityClass1;
		this.entityClass2 = entityClass2;
		tableName = DBTableNameAware.getTableNameByClass(entityClass1, entityClass2);
	}

	@Override
	public List<Map> entityToMap(Object entity1) {
		return null;
	}

	@Override
	public void insert(Object entity1) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);

		List<Map> paramsList = entityToMap(entity1);
		
		for (Map params : paramsList) {
			insert.execute(params);
		}
	}

	@Override
	public void deleteByFirstId(Object id) {
		jdbcTemplate.update(String.format("DELETE FROM %s WHERE %s_id = ?", tableName, entityClass1.getSimpleName().toLowerCase()), id);
	}

	@Override
	public void deleteBySecondId(Object id) {
		jdbcTemplate.update(String.format("DELETE FROM %s WHERE %s_id = ?", tableName, entityClass2.getSimpleName().toLowerCase()), id);
	}
}
