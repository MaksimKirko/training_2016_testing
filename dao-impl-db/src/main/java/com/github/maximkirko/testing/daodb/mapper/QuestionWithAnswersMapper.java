package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

public class QuestionWithAnswersMapper implements IGenericMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

		Question question = new Question();
		question.setText(rs.getString("text"));
		question.setHint(rs.getString("hint"));
		question.setId(rs.getLong("id"));

		Answer answer = new Answer();
		answer.setText(rs.getString(5));
		answer.setCorrectness(rs.getBoolean("correctness"));
		answer.setQuestion(question);
		answer.setId(rs.getLong(4));

		List<Answer> answers = new ArrayList<Answer>();
		answers.add(answer);

		question.setAnswers(answers);

		return question;
	}

}
