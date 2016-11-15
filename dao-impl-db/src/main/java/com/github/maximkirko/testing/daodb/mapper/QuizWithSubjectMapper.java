package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class QuizWithSubjectMapper implements RowMapper<Quiz> {
	
	@Override
	public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString("title"));
		quiz.setDescription(rs.getString("description"));
		quiz.setId(rs.getLong("id"));
		
		Subject subject = new SubjectMapper().mapRow(rs, rowNum);
		
		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizzes.add(quiz);
		subject.setQuizzes(quizzes);
		
		quiz.setSubject(subject);

		return quiz;
	}
}
