package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;

public class GradeMapper implements IGenericMapper<Grade> {

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {

		Quiz quiz = new Quiz();
		quiz.setId(rs.getLong("quiz_id"));

		User user = new User();
		user.setId(rs.getLong("user_id"));

		Grade grade = new Grade();
		grade.setMark(rs.getFloat("mark"));
		grade.setQuiz(quiz);
		grade.setUser(user);
		grade.setId(rs.getLong("id"));

		return grade;
	}

}
