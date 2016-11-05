package com.github.maximkirko.testing.daodb.impl;

import java.lang.reflect.Field;
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

	private String tableName;

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
		String values = "";

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				values += String.format("%s=%s, ", field.getName(), field.get(entity));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		values = values.substring(0, values.length() - 2);

		String sql = String.format("UPDATE %s SET %s WHERE id=%s;", tableName, values, entity.getId());

		jdbcTemplate.execute(sql);
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.execute(String.format("DELETE FROM %s WHERE id=%s", tableName, id));
	}

	@Override
	public List getAll() {
		return jdbcTemplate.query(String.format("SELECT * FROM ", tableName),
				new BeanPropertyRowMapper<T>(entityClass));
	}
}
