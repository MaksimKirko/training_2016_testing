package com.github.maximkirko.testing.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGenericManyToManyDao;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.daodb.util.GenericTypeInfo;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public class GenericManyToManyDaoImpl<T, PK1, PK2> implements IGenericManyToManyDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private Class<T> entityClass;

	protected String tableName;
	
	public GenericManyToManyDaoImpl() {

	}

	public GenericManyToManyDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
	}

	@Override
	public void insert(AbstractModel entity) {
		
		final String INSERT_SQL = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName,
				GenericTypeInfo.getFields(entityClass, entity), GenericTypeInfo.getFieldsValues(entityClass, entity));

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				System.out.println(ps.toString());
				return ps;
			}
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void deleteByFirstId(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBySecondId(Object id) {
		// TODO Auto-generated method stub
		
	}

	
}
