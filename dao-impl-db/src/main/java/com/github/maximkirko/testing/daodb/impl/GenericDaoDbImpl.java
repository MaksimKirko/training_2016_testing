package com.github.maximkirko.testing.daodb.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGenericDao;
import com.github.maximkirko.testing.daodb.entitytomap.EntityToMap;
import com.github.maximkirko.testing.daodb.util.GenericTypeFieldsAware;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public abstract class GenericDaoDbImpl<T extends AbstractModel, PK extends Serializable> implements IGenericDao<T, PK> {

	@Inject
	protected JdbcTemplate jdbcTemplate;

	protected Class<T> entityClass;

	protected String tableName;

	protected RowMapper<T> mapper;
	
	protected EntityToMap<T> entityToMap;

	public GenericDaoDbImpl() {

	}

	public GenericDaoDbImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
		this.mapper = new BeanPropertyRowMapper<T>(entityClass);
	}

	public GenericDaoDbImpl(Class<T> entityClass, RowMapper<T> mapper) {
		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
		this.mapper = mapper;
	}

	@Override
	public T get(PK id) {
		
		T entity;
		
		try {
			entity = jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE id = ?", tableName),
					new Object[] { id }, mapper);
			
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
		
		return entity;
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query(String.format("SELECT * FROM %s", tableName), mapper);
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK insert(T newEntity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);
		insert.usingGeneratedKeyColumns("id");

		Map<String, Object> params = entityToMap.convert(newEntity);

		Long id = insert.executeAndReturnKey(params).longValue();

		return (PK) id;
	}

	@Override
	public void update(T entity) {

		final String sql = String.format("UPDATE %s SET %s WHERE id=?", tableName,
				GenericTypeFieldsAware.getStringForUpdate(entityClass, entity));

		jdbcTemplate.update(sql, entity.getId());
	}

	@Override
	public void delete(PK id) {
		jdbcTemplate.update(String.format("DELETE FROM %s WHERE id = ?", tableName), id);
	}

}