package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.customentity.QuestionToAnswer;

public interface IQuestionToAnswerDao {

	List<QuestionToAnswer> getByQuestion(Question question);

	List<QuestionToAnswer> getByAnswer(Answer answer);

	void insert(QuestionToAnswer entity);

	void deleteByQuestion(Question question);

	void deleteByAnswer(Answer answer);

}
