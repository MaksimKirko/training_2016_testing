package com.github.maximkirko.testing.daodb.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGenericDao;
import com.github.maximkirko.testing.daodb.entitytomap.IEntityToMap;
import com.github.maximkirko.testing.daodb.mapper.IGenericMapper;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public abstract class GenericDaoDbImpl<T extends AbstractModel, PK extends Serializable> implements IGenericDao<T, PK> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	private String tableName;
	private IGenericMapper<T> mapper;
	private IEntityToMap<T> entityToMap;

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected String getTableName() {
		return tableName;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	protected IGenericMapper<T> getMapper() {
		return mapper;
	}

	protected void setMapper(IGenericMapper<T> mapper) {
		this.mapper = mapper;
	}

	protected IEntityToMap<T> getEntityToMap() {
		return entityToMap;
	}

	protected void setEntityToMap(IEntityToMap<T> entityToMap) {
		this.entityToMap = entityToMap;
	}

	public GenericDaoDbImpl() {

	}

	@Override
	public T get(PK id) {

		T entity;

		try {
			entity = jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE id = ?", tableName),
					new Object[] { id }, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return entity;
	}

	@Override
	public List<T> getAll() {

		List<T> entities;

		try {
			entities = jdbcTemplate.query(String.format("SELECT * FROM %s", tableName), mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		return entities;
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

		Map<String, Object> params = entityToMap.convert(entity);
		String values = getValuesForUpdate(params);

		String sql = String.format("UPDATE %s SET %s WHERE id=?", tableName, values);

		jdbcTemplate.update(sql, entity.getId());
	}

	private String getValuesForUpdate(Map<String, Object> params) {

		String values = "";

		for (Map.Entry<String, Object> entry : params.entrySet()) {

			if (!entry.getKey().equals("id") && entry.getValue() != null) {
				if (entry.getValue().getClass().equals(String.class)) {
					values += entry.getKey() + "='" + entry.getValue() + "'";
				} else {
					values += entry.getKey() + "=" + entry.getValue();
				}
				values += ", ";
			}
		}

		values = values.substring(0, values.length() - 2);

		return values;

	}

	@Override
	public void delete(PK id) {

		jdbcTemplate.update(String.format("DELETE FROM %s WHERE id = ?", tableName), id);
	}

}