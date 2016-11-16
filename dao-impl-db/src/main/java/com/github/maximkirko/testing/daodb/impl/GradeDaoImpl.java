package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daodb.IGradeDao;
import com.github.maximkirko.testing.daodb.mapper.GradeMapper;
import com.github.maximkirko.testing.daodb.mapper.GradeWithStudentAndQuizMapper;
import com.github.maximkirko.testing.daodb.util.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class GradeDaoImpl extends GenericDaoImpl<Grade, Long> implements IGradeDao {

	private String studentTableName;
	private String quizTableName;

	public GradeDaoImpl() {
		super(Grade.class, new GradeMapper());
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
	public Map<String, Object> entityToMap(Grade entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mark", entity.getMark());
		params.put("student_id", entity.getStudent().getId());
		params.put("quiz_id", entity.getQuiz().getId());
		params.put("id", entity.getId());

		return params;
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
