package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.GenericDao;
import com.github.maximkirko.testing.daodb.mapper.*;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;
import com.github.maximkirko.testing.datamodel.models.Quiz;

@Repository
public class GenericDaoImpl<T extends AbstractModel> implements GenericDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private String tableName;
	
	private RowMapper rowMapper;

	public GenericDaoImpl(RowMapper<T> rowMapper, String tableName) {
		this.rowMapper = rowMapper;
		this.tableName = tableName;
	}

	@Override
	public Object get(Long id) {
		return jdbcTemplate.queryForObject("select * from" + tableName + "where id = ?", new Object[] { id }, rowMapper);
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
