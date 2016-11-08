package com.github.maximkirko.testing.daodb.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGenericDao;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.daodb.util.GenericTypeInfo;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public abstract class GenericDaoImpl<T extends AbstractModel, PK extends Serializable> implements IGenericDao<T, PK> {

	@Inject
	protected JdbcTemplate jdbcTemplate;

	protected Class<T> entityClass;

	protected String tableName;

	public GenericDaoImpl() {

	}

	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
	}

	@Override
	public Map entityToMap(T entity) {
		return null;
	}

	@Override
	public T get(PK id) {
		return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE id = ?", tableName),
				new Object[] { id }, new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	public List getAll() {
		return jdbcTemplate.query(String.format("SELECT * FROM %s", tableName),
				new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK insert(T entity) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName(tableName);
		insert.usingGeneratedKeyColumns("id");

		Map params = entityToMap(entity);
		
		Long id = insert.executeAndReturnKey(params).longValue();
		
		return (PK) id;
	}

	@Override
	public void update(T entity) {

		final String sql = String.format("UPDATE %s SET %s WHERE id=?", tableName,
				GenericTypeInfo.getValuesForUpdate(entityClass, entity));

		jdbcTemplate.update(sql, entity.getId());
	}

	@Override
	public void delete(PK id) {
		jdbcTemplate.update(String.format("DELETE FROM %s WHERE id = ?", tableName), id);
	}
}