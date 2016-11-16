package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

public class StudentWithRoleMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		Student student = new Student();
		student.setFirstName(rs.getString("first_name"));
		student.setLastName(rs.getString("last_name"));
		student.setAge(rs.getInt("age"));
		student.setCourse(rs.getString("course"));
		student.setEmail(rs.getString("email"));
		student.setPassword(rs.getString("password"));

		Role role = new RoleMapper().mapRow(rs, rowNum);
		
		List<Student> students = new ArrayList<Student>();
		students.add(student);
		role.setStudents(students);
		
		student.setRole(role);

		return student;
	}

}
