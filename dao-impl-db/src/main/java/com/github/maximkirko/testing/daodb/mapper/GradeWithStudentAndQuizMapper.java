package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.models.User;

public class GradeWithStudentAndQuizMapper implements IGenericMapper<Grade> {

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {

		Grade grade = new Grade();
		grade.setMark(rs.getFloat("mark"));
		grade.setId(rs.getLong("id"));

		User user = new User();
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setAge(rs.getInt("age"));
		user.setCourse(rs.getString("course"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));

		Role role = new Role();
		role.setId(rs.getLong("role_id"));
		user.setRole(role);

		user.setId(rs.getLong("user_id"));

		Subject subject = new Subject();
		subject.setId(rs.getLong("subject_id"));

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString("title"));
		quiz.setDescription(rs.getString("description"));
		quiz.setSubject(subject);
		quiz.setId(rs.getLong("quiz_id"));

		List<Grade> grades = new ArrayList<Grade>();
		grades.add(grade);

		user.setGrades(grades);
		quiz.setGrades(grades);

		grade.setUser(user);
		grade.setQuiz(quiz);

		return grade;
	}
}
