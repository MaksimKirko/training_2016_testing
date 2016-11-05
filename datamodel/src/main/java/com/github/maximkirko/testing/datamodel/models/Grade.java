package com.github.maximkirko.testing.datamodel.models;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "grade")
public class Grade extends AbstractModel {
	private double mark;

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		return "Grade [mark=" + mark + "]";
	}

	public Grade() {

	}
}
