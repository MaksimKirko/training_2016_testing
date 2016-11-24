package com.github.maximkirko.testing.datamodel.models;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.annotations.Filename;

@DBTable("grade")
@Filename("grade.xml")
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(mark);
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade other = (Grade) obj;
		if (Float.floatToIntBits(mark) != Float.floatToIntBits(other.mark))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	public Grade() {

	}
}
