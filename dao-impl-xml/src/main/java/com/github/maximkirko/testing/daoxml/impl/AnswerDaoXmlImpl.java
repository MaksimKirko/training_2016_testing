package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IAnswerDao;
import com.github.maximkirko.testing.datamodel.models.Answer;

@Repository
public class AnswerDaoXmlImpl extends GenericDaoXmlImpl<Answer, Long> implements IAnswerDao {
	
	public AnswerDaoXmlImpl() throws IOException {
		super(Answer.class);
	}

	@Override
	public List<Answer> getByQuestionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}