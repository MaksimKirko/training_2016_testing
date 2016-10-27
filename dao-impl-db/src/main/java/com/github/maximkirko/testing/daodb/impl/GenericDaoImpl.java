package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.GenericDao;
import com.github.maximkirko.testing.daodb.util.Utils;

@Repository
public class GenericDaoImpl<T> implements GenericDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private Class<T> entityClass;

	private String tableName;
	
	public GenericDaoImpl() {
		
	}

	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		tableName = Utils.getTableNameByClass(entityClass);
	}

	@Override
	public Object get(Long id) {
		return jdbcTemplate.queryForObject("select * from " + tableName + " where id = ?",
				new Object[] { id }, new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	public void insert(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
