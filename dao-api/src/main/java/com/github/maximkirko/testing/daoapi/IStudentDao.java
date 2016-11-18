package com.github.maximkirko.testing.daoapi;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

public interface IStudentDao extends IGenericDao<Student, Long> {
	
	Student getWithRole(Long id);
	
	List<Student> getByRole(Role role);
	
}
