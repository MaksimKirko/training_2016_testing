package com.github.maximkirko.testing.daodb;

import java.util.List;

import com.github.maximkirko.testing.daodb.customentity.QuizToQuestion;
import com.github.maximkirko.testing.datamodel.models.Question;
import com.github.maximkirko.testing.datamodel.models.Quiz;

public interface IQuizToQuestionDao extends IGenericManyToManyDao<Quiz, Question, Long, Long> {
	
	List<QuizToQuestion> getByQuizId(Long id);
}
