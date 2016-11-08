package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.users.StudentDetails;

public interface IStudentDetailsService {

	StudentDetails get(Long id);

	List getAll();

	Long save(StudentDetails studentDetails);

	void saveAll(List<StudentDetails> studentDetails);

	void delete(Long id);
}
