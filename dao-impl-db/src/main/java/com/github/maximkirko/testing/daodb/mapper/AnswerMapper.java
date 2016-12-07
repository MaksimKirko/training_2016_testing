package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Answer;

public class AnswerMapper implements IGenericMapper<Answer> {

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Answer answer = new Answer();
		answer.setText(rs.getString("text"));
		answer.setId(rs.getLong("id"));

		return answer;
	}

}
