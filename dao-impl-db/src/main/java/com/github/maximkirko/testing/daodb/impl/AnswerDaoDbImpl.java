package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IAnswerDao;
import com.github.maximkirko.testing.daodb.entitytomap.AnswerToMap;
import com.github.maximkirko.testing.daodb.mapper.AnswerMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Answer;

@Repository
public class AnswerDaoDbImpl extends GenericDaoDbImpl<Answer, Long> implements IAnswerDao {

	public AnswerDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(Answer.class));
		setEntityToMap(new AnswerToMap());
		setMapper(new AnswerMapper());
	}

	@Override
	public List<Answer> getByQuestionId(Long id) {
		
		List<Answer> answers;

		try {
			answers = getJdbcTemplate().query(String.format("SELECT * FROM %s WHERE question_id = ?", getTableName()),
					new Object[] { id }, getMapper());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return answers;
	}

}
