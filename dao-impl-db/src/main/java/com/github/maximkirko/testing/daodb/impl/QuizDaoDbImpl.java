package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuizDao;
import com.github.maximkirko.testing.daodb.entitytomap.QuizToMap;
import com.github.maximkirko.testing.daodb.mapper.QuizMapper;
import com.github.maximkirko.testing.daodb.mapper.QuizWithQuestionsMapper;
import com.github.maximkirko.testing.daodb.mapper.QuizWithSubjectMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;
import com.github.maximkirko.testing.datamodel.models.Subject;
import com.github.maximkirko.testing.datamodel.models.customentity.QuizToQuestion;

@Repository
public class QuizDaoDbImpl extends GenericDaoDbImpl<Quiz, Long> implements IQuizDao {

	private String subjectTableName;
	private String quiz2questionTableName;
	private String questionTableName;
	private QuizWithSubjectMapper quizWithSubjectMapper;
	private QuizWithQuestionsMapper quizWithQuestionsMapper;

	public QuizDaoDbImpl() {
		setTableName(DBTableNameAware.getTableNameByClass(Quiz.class));
		setEntityToMap(new QuizToMap());
		setMapper(new QuizMapper());

		subjectTableName = DBTableNameAware.getTableNameByClass(Subject.class);
		quiz2questionTableName = DBTableNameAware.getTableNameByClass(QuizToQuestion.class);
		questionTableName = DBTableNameAware.getTableNameByClass(Question.class);
		quizWithSubjectMapper = new QuizWithSubjectMapper();
		quizWithQuestionsMapper = new QuizWithQuestionsMapper();
	}

	@Override
	public Quiz getWithSubject(Long id) {

		return getJdbcTemplate()
				.queryForObject(String.format("SELECT * FROM %s q LEFT JOIN %s s ON q.subject_id=s.id WHERE q.id = ?",
						getTableName(), subjectTableName), new Object[] { id }, quizWithSubjectMapper);
	}

	@Override
	public List<Quiz> getBySubjectId(Long id) {

		return getJdbcTemplate().query(String.format("SELECT * FROM %s WHERE subject_id = ?", getTableName()),
				new Object[] { id }, getMapper());
	}

	@Override
	public Quiz getWithQuestions(Long id) {

		Quiz quiz;
		List<Quiz> quizzes;

		try {
			quizzes = getJdbcTemplate().query(
					String.format(
							"SELECT * FROM %s q LEFT JOIN %s q2q ON q.id=q2q.quiz_id LEFT JOIN %s q2 ON q2q.question_id=q2.id WHERE q.id = ?",
							getTableName(), quiz2questionTableName, questionTableName),
					new Object[] { id }, quizWithQuestionsMapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		if (quizzes.size() < 1) {
			return null;
		}
		quiz = quizzes.get(0);
		quizzes.remove(0);
		List<Question> questions = quiz.getQuestions();
		for (Quiz q : quizzes) {
			questions.addAll(q.getQuestions());
		}
		quiz.setQuestions(questions);

		return quiz;
	}
}