package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IGradeDao;
import com.github.maximkirko.testing.daodb.entitytomap.GradeToMap;
import com.github.maximkirko.testing.daodb.mapper.GradeMapper;
import com.github.maximkirko.testing.daodb.mapper.GradeWithStudentAndQuizMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.User;

@Repository
public class GradeDaoDbImpl extends GenericDaoDbImpl<Grade, Long> implements IGradeDao {

	private String studentTableName;
	private String quizTableName;
	private GradeWithStudentAndQuizMapper gradeWithStudentAndQuizMapper;

	public GradeDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(Grade.class));
		setEntityToMap(new GradeToMap());
		setMapper(new GradeMapper());

		studentTableName = DBTableNameAware.getTableNameByClass(User.class);
		quizTableName = DBTableNameAware.getTableNameByClass(Quiz.class);
		gradeWithStudentAndQuizMapper = new GradeWithStudentAndQuizMapper();
	}

	@Override
	public Grade getWithUserAndQuiz(Long id) {

		Grade grade;

		try {
			grade = getJdbcTemplate().queryForObject(
					String.format(
							"SELECT * FROM %s g LEFT JOIN %s u ON g.user_id=u.id LEFT JOIN %s q ON g.quiz_id=q.id WHERE g.id = ?",
							getTableName(), studentTableName, quizTableName),
					new Object[] { id }, gradeWithStudentAndQuizMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return grade;
	}

	@Override
	public List<Grade> getAll() {

		List<Grade> grades;

		try {
			grades = getJdbcTemplate().query(String.format(
					"SELECT * FROM %s g LEFT JOIN %s s ON g.user_id=s.id LEFT JOIN %s q ON g.quiz_id=q.id",
					getTableName(), studentTableName, quizTableName), gradeWithStudentAndQuizMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return grades;
	}

	@Override
	public List<Grade> getByUserId(Long id) {

		List<Grade> grades;
		try {
			grades = getJdbcTemplate().query(
					String.format(
							"SELECT * FROM %s g LEFT JOIN %s u ON g.user_id=u.id LEFT JOIN %s q ON g.quiz_id=q.id WHERE g.user_id = ?",
							getTableName(), studentTableName, quizTableName),
					new Object[] { id }, gradeWithStudentAndQuizMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return grades;
	}

	@Override
	public List<Grade> getByQuizId(Long id) {
		List<Grade> grades;
		try {
			grades = getJdbcTemplate().query(
					String.format(
							"SELECT * FROM %s g LEFT JOIN %s u ON g.user_id=u.id LEFT JOIN %s q ON g.quiz_id=q.id WHERE g.quiz_id = ?",
							getTableName(), studentTableName, quizTableName),
					new Object[] { id }, gradeWithStudentAndQuizMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return grades;
	}

}
