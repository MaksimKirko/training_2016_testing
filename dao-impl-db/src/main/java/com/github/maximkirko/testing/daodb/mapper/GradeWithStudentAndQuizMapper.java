package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

public class GradeWithStudentAndQuizMapper implements RowMapper<Grade> {

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {

		Grade grade = new Grade();
		grade.setMark(rs.getFloat("mark"));
		grade.setId(rs.getLong("id"));

		Student student = new StudentMapper().mapRow(rs, rowNum);
		Quiz quiz = new QuizMapper().mapRow(rs, rowNum);

		List<Grade> grades = new ArrayList<Grade>();
		grades.add(grade);
		
		student.setGrades(grades);
		quiz.setGrades(grades);

		grade.setStudent(student);
		grade.setQuiz(quiz);

		return grade;
	}
}