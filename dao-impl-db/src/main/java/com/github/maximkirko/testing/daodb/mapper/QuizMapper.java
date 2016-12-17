package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class QuizMapper implements IGenericMapper<Quiz> {
	@Override
	public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {

		Subject subject = new Subject();
		subject.setId(rs.getLong("subject_id"));

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString("title"));
		quiz.setDescription(rs.getString("description"));
		quiz.setSubject(subject);
		quiz.setId(rs.getLong("id"));

		return quiz;
	}

}
