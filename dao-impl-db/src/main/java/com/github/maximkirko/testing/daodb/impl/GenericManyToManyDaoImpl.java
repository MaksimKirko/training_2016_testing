package com.github.maximkirko.testing.daodb.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGenericManyToManyDao;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.daodb.util.GenericTypeFieldsAware;

@Repository
public class GenericManyToManyDaoImpl<T, PK1, PK2> implements IGenericManyToManyDao<T, PK1, PK2> {

	@Inject
	protected JdbcTemplate jdbcTemplate;

	protected Class<T> entityClass;

	protected List<String> dbFields;

	protected String tableName;

	protected RowMapper<T> mapper;

	public GenericManyToManyDaoImpl() {

	}

	public GenericManyToManyDaoImpl(Class<T> entityClass, RowMapper<T> mapper) {

		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
		dbFields = DBTableNameAware.getManyToManyTableFields(tableName);
		this.mapper = mapper;

	}

	@Override
	public List<T> getByFirstId(PK1 id) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE %s = ?", tableName, dbFields.get(0)),
				new Object[] { id }, mapper);
	}

	@Override
	public List<T> getBySecondId(PK2 id) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE %s = ?", tableName, dbFields.get(1)),
				new Object[] { id }, mapper);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void insert(Object entity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);

		Map<String, Object> params = GenericTypeFieldsAware.getManyToManyFieldsMap(entityClass, entity);

		insert.execute(params);

	}

	@Override
	public void deleteByFirstId(Object id) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE %s = ?", tableName, dbFields.get(0)), id);
	}

	@Override
	public void deleteBySecondId(Object id) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE %s = ?", tableName, dbFields.get(1)), id);
	}

}
