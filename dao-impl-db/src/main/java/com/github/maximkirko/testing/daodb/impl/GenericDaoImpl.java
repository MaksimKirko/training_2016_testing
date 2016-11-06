package com.github.maximkirko.testing.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGenericDao;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.daodb.util.GenericTypeInfo;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public class GenericDaoImpl<T extends AbstractModel> implements IGenericDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private Class<T> entityClass;

	protected String tableName;

	public GenericDaoImpl() {

	}

	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		tableName = DBTableNameAware.getTableNameByClass(entityClass);
	}

	@Override
	public AbstractModel get(Long id) {
		return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE id =%s", tableName, "?"),
				new Object[] { id }, new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	public Long insert(AbstractModel entity) {

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

		return entity.getId();
	}

	@Override
	public void update(AbstractModel entity) {

		final String sql = String.format("UPDATE %s SET %s WHERE id=?", tableName,
				GenericTypeInfo.getValuesForUpdate(entityClass, entity));

		jdbcTemplate.update(sql, entity.getId());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update(String.format("DELETE FROM %s WHERE id=?", tableName, id));
	}

	@Override
	public List getAll() {
		return jdbcTemplate.query(String.format("SELECT * FROM ", tableName),
				new BeanPropertyRowMapper<T>(entityClass));
	}
}
