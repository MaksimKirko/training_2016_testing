package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface GenericMapper<T> extends RowMapper<T> {
	@Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
