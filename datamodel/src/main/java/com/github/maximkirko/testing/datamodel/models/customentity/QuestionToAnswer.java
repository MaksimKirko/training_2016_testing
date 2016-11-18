package com.github.maximkirko.testing.datamodel.models.customentity;

import java.util.ArrayList;
import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.models.Answer;
import com.github.maximkirko.testing.datamodel.models.Question;

@DBTable(name = "question_2_answer")
public class QuestionToAnswer implements ICustomEntity {

	private Question question;
	private Answer answer;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public static List<QuestionToAnswer> questionQTAList(Question question) {

		List<QuestionToAnswer> questionToAnswers = new ArrayList<QuestionToAnswer>();
		for (Answer answer : question.getAnswers()) {

			QuestionToAnswer questionToAnswer = new QuestionToAnswer();

			questionToAnswer.setQuestion(question);
			questionToAnswer.setAnswer(answer);

			questionToAnswers.add(questionToAnswer);
		}

		return questionToAnswers;
	}
	
	public static List<QuestionToAnswer> answerQTAList(Answer answer) {

		List<QuestionToAnswer> questionToAnswers = new ArrayList<QuestionToAnswer>();
		for (Question question : answer.getQuestions()) {

			QuestionToAnswer questionToAnswer = new QuestionToAnswer();

			questionToAnswer.setQuestion(question);
			questionToAnswer.setAnswer(answer);

			questionToAnswers.add(questionToAnswer);
		}

		return questionToAnswers;
	}

}
