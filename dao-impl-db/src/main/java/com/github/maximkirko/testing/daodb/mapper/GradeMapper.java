package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

public class GradeMapper implements RowMapper<Grade> {
	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Quiz quiz = new Quiz();
		quiz.setId(rs.getLong("quiz_id"));

		Student student = new Student();
		student.setId(rs.getLong("student_id"));
		
		Grade grade = new Grade();
		grade.setMark(rs.getFloat("mark"));
		grade.setQuiz(quiz);
		grade.setStudent(student);
		grade.setId(rs.getLong("id"));

		return grade;
	}

}
