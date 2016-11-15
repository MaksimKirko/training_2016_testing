package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;

@DBTable(name = "role")
public class Role extends AbstractModel {

	private enum RoleEnum {
		ADMIN, STUDENT, GUEST;
	}

	private RoleEnum type;
	private List<Student> students;

	public RoleEnum getType() {
		return type;
	}

	public void setType(RoleEnum type) {
		this.type = type;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Role [type=" + type + ", students=" + students + "]";
	}
	
}
