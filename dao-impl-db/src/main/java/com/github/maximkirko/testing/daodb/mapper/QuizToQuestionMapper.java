package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public class QuizToQuestionMapper implements IGenericMapper<QuizToQuestion> {
	@Override
	public QuizToQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {

		QuizToQuestion quizToQuestion = new QuizToQuestion();
		quizToQuestion.setQuizId(rs.getLong("quiz_id"));
		quizToQuestion.setQuestionId(rs.getLong("question_id"));

		return quizToQuestion;
	}
}
