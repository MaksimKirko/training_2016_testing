package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IQuestionDao;
import com.github.maximkirko.testing.datamodel.models.Question;

@Repository
public class QuestionDaoXmlImpl extends GenericDaoXmlImpl<Question, Long> implements IQuestionDao {

	public QuestionDaoXmlImpl() throws IOException {
		super(Question.class);
	}

}
