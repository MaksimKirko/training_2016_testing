package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

public class AnswerMapper implements IGenericMapper<Answer> {

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Answer answer = new Answer();
		answer.setText(rs.getString("text"));
		answer.setCorrectness(rs.getBoolean("correctness"));

		Question question = new Question();
		question.setId(rs.getLong("question_id"));

		answer.setQuestion(question);
		answer.setId(rs.getLong("id"));

		return answer;
	}

}
