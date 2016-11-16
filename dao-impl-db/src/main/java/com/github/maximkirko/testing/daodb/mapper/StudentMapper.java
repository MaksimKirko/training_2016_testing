package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		Student student = new Student();
		student.setFirstName(rs.getString("first_name"));
		student.setLastName(rs.getString("last_name"));
		student.setAge(rs.getInt("age"));
		student.setCourse(rs.getString("course"));
		student.setEmail(rs.getString("email"));
		student.setPassword(rs.getString("password"));
		
		Role role = new Role();
		role.setId(rs.getLong("role_id"));
		student.setRole(role);
		
		student.setId(rs.getLong("id"));

		return student;
	}


}
