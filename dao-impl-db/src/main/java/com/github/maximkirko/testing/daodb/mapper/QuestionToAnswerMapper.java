package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;

public class QuestionToAnswerMapper implements IGenericMapper<QuestionToAnswer> {

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
