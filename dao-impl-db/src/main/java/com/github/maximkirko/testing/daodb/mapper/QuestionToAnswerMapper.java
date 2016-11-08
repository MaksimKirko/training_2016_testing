package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

public class QuestionToAnswerMapper implements RowMapper<QuestionToAnswer> {
	
	@Override
	public QuestionToAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Question question = new Question();
		question.setId(rs.getLong("question_id"));

		Answer answer = new Answer();
		answer.setId(rs.getLong("answer_id"));

		QuestionToAnswer questionToAnswer = new QuestionToAnswer();
		questionToAnswer.setQuestion(question);
		questionToAnswer.setAnswer(answer);

		return questionToAnswer;
	}

}
