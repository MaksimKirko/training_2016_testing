package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ManyToManyMapper<T> implements RowMapper<T> {

	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}
}
