package com.github.maximkirko.testing.daodb.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.GenericDao;
import com.github.maximkirko.testing.daodb.util.Utils;
import com.github.maximkirko.testing.datamodel.models.AbstractModel;

@Repository
public class GenericDaoImpl<T extends AbstractModel> implements GenericDao {

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
		return jdbcTemplate.queryForObject("SELECT * FROM " + tableName + " WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	@SuppressWarnings("unchecked")
	public void insert(Object entity) {

		String types = "";
		String values = "";

		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				if (field.get(entity) != null) {
					types += String.format("%s, ", field.getName());
					values += String.format("'%s', ", field.get(entity));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		types = types.substring(0, types.length() - 2);
		values = values.substring(0, values.length() - 2);

		String sql = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, types, values);
		System.out.println(sql);
		jdbcTemplate.execute(sql);
	}

	@Override
	public void update(Object entity) {
//		UPDATE link_tmp
//		SET rel = link.rel,
//		 description = link.description,
//		 last_update = link.last_update
//		FROM
//		 link
//		WHERE
//		 link_tmp.id = link.id;
		
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
		
		String sql = String.format("UPDATE %s SET %s WHERE id=%s;", tableName, values, "?");
		
		jdbcTemplate.execute(sql);
		
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.execute("DELETE FROM " + tableName + " WHERE id=" + id);
	}

	@Override
	public List getAll() {
		return jdbcTemplate.query("SELECT * FROM " + tableName, new BeanPropertyRowMapper<T>(entityClass));
	}
}
