package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class QuizWithSubjectMapper implements IGenericMapper<Quiz> {

	@Override
	public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString("title"));
		quiz.setDescription(rs.getString("description"));
		quiz.setId(rs.getLong("id"));

		Subject subject = new Subject();
		subject.setTitle(rs.getString(6));
		subject.setDescription(rs.getString(7));
		subject.setId(rs.getLong(5));

		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizzes.add(quiz);
		subject.setQuizzes(quizzes);

		quiz.setSubject(subject);

		return quiz;
	}
}
