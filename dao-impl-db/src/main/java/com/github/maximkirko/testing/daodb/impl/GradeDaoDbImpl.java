package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.daodb.entitytomap.GradeToMap;
import com.github.maximkirko.testing.daodb.mapper.GradeMapper;
import com.github.maximkirko.testing.daodb.mapper.GradeWithStudentAndQuizMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class GradeDaoDbImpl extends GenericDaoDbImpl<Grade, Long> implements IGradeDao {

	private String studentTableName;
	private String quizTableName;

	public GradeDaoDbImpl() {
		super(Grade.class, new GradeMapper(), new GradeToMap());
		studentTableName = DBTableNameAware.getTableNameByClass(Student.class);
		quizTableName = DBTableNameAware.getTableNameByClass(Quiz.class);
	}

	@Override
	public Grade getWithStudentAndQuiz(Long id) {

		return jdbcTemplate.queryForObject(String.format(
				"SELECT * FROM %s g LEFT JOIN %s s ON g.student_id=s.id LEFT JOIN %s q ON g.quiz_id=q.id WHERE g.id = ?",
				super.tableName, studentTableName, quizTableName), new Object[] { id }, new GradeWithStudentAndQuizMapper());
	}

	@Override
	public List<Grade> getByStudent(Student student) {
		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE student_id = ?", super.tableName),
				new Object[] { student.getId() }, mapper);
	}

	@Override
	public List<Grade> getByQuiz(Quiz quiz) {
		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE quiz_id = ?", super.tableName),
				new Object[] { quiz.getId() }, mapper);
	}

}
