package com.github.maximkirko.testing.datamodel.models;

import java.util.List;

import com.github.maximkirko.testing.datamodel.annotations.DBTable;
import com.github.maximkirko.testing.datamodel.annotations.Filename;

@DBTable("role")
@Filename("role.xml")
public class Role extends AbstractModel {

	public enum RoleEnum {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Role other = (Role) obj;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
