package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

public class UserWithGradesMapper implements IGenericMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setAge(rs.getInt("age"));
		user.setCourse(rs.getString("course"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setId(rs.getLong("id"));

		Role role = new Role();
		role.setId(rs.getLong("role_id"));
		user.setRole(role);
		
		Grade grade = new Grade();
		grade.setMark(rs.getFloat("mark"));
		Quiz quiz = new Quiz();
		quiz.setId(rs.getLong("quiz_id"));
		grade.setQuiz(quiz);
		grade.setUser(user);
		grade.setId(rs.getLong(9));
		
		List<Grade> grades = new ArrayList<>();
		grades.add(grade);
		user.setGrades(grades);
		
		return user;
	}

}
