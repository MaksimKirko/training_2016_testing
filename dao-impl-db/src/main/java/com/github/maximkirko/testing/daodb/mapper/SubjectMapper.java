package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Subject;

public class SubjectMapper implements RowMapper<Subject> {
	
	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Subject subject = new Subject();
		subject.setTitle(rs.getString("title"));
		subject.setDescription(rs.getString("description"));
		subject.setId(rs.getLong("id"));

		return subject;
	}

}
