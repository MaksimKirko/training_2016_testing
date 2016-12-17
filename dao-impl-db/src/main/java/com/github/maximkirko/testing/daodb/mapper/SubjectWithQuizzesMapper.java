package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class SubjectWithQuizzesMapper implements IGenericMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {

		Subject subject = new Subject();
		subject.setTitle(rs.getString("title"));
		subject.setDescription(rs.getString("description"));
		subject.setId(rs.getLong("id"));

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString(6));
		quiz.setDescription(rs.getString(7));
		quiz.setSubject(subject);
		quiz.setId(rs.getLong(4));

		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizzes.add(quiz);

		subject.setQuizzes(quizzes);

		return subject;
	}

}
