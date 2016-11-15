package com.github.maximkirko.testing.datamodel.models;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "grade")
public class Grade extends AbstractModel {
	
	private float mark;
	private Student student;
	private Quiz quiz;

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return "Grade [mark=" + mark + ", student=" + student + ", quiz=" + quiz + "]";
	}

	public Grade() {

	}
}
