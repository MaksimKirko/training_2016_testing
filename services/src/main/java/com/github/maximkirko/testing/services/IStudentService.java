package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

public interface IStudentService extends IGenericService<Student, Long> {

	Student getWithRole(Long id);
	
	List<Student> getByRole(Role role);
	
	Student getWithGrades(Long id);
	
}
