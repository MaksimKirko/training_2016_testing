package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;

public class QuizWithQuestionsMapper implements IGenericMapper<Quiz> {
	@Override
	public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {

		Subject subject = new Subject();
		subject.setId(rs.getLong("subject_id"));

		Quiz quiz = new Quiz();
		quiz.setTitle(rs.getString("title"));
		quiz.setDescription(rs.getString("description"));
		quiz.setSubject(subject);
		quiz.setId(rs.getLong("id"));

		
		List<Question> questions = new ArrayList<>();
		Question question = new Question();
		question.setText(rs.getString("text"));
		question.setHint(rs.getString("hint"));
		question.setId(rs.getLong(7));
		questions.add(question);
		
		quiz.setQuestions(questions);
		
		return quiz;
	}

}
