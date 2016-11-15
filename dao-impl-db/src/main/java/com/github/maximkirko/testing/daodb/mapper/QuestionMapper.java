package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Question;

public class QuestionMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

		Question question = new Question();
		question.setText(rs.getString("text"));
		question.setHint(rs.getString("hint"));
		question.setId(rs.getLong("id"));

		return question;
	}

}
