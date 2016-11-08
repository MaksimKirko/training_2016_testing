package com.github.maximkirko.testing.daodb;

import java.util.List;

import com.github.maximkirko.testing.daodb.customentity.QuestionToAnswer;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

public interface IQuestionToAnswerDao extends IGenericManyToManyDao<Question, Answer, Long, Long> {

	List<QuestionToAnswer> getByQuestionId(Long id);
	
}
