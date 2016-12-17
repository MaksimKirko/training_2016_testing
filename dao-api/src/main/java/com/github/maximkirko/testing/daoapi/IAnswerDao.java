package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;

public interface IAnswerDao extends IGenericDao<Answer, Long> {

	List<Answer> getByQuestionId(Long id);

}
