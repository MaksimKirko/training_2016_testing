package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public interface IGenericMapper<T> extends RowMapper<T> {

	@Override
	T mapRow(ResultSet rs, int rowNum) throws SQLException;

}
