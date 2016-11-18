package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

public class QuizToQuestionMapper implements RowMapper<QuizToQuestion> {
	@Override
	public QuizToQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {

		Quiz quiz = new Quiz();
		quiz.setId(rs.getLong("quiz_id"));

		Question question = new Question();
		question.setId(rs.getLong("question_id"));

		QuizToQuestion quizToQuestion = new QuizToQuestion();
		quizToQuestion.setQuiz(quiz);
		quizToQuestion.setQuestion(question);

		return quizToQuestion;
	}
}
