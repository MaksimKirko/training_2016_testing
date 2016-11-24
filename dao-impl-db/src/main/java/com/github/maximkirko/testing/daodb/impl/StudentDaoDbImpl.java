package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IStudentDao;
import com.github.maximkirko.testing.daodb.entitytomap.StudentToMap;
import com.github.maximkirko.testing.daodb.mapper.StudentMapper;
import com.github.maximkirko.testing.daodb.mapper.StudentWithRoleMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class StudentDaoDbImpl extends GenericDaoDbImpl<Student, Long> implements IStudentDao {
	
	private String roleTableName;
	
	public StudentDaoDbImpl() {
		super(Student.class, new StudentMapper(), new StudentToMap());
		roleTableName = DBTableNameAware.getTableNameByClass(Role.class);
	}

	@Override
	public Student getWithRole(Long id) {
		return jdbcTemplate
				.queryForObject(String.format("SELECT * FROM %s s LEFT JOIN %s r ON s.role_id=r.id WHERE s.id = ?",
						super.tableName, roleTableName), new Object[] { id }, new StudentWithRoleMapper());
	}

	@Override
	public List<Student> getByRole(Role role) {
		
		return jdbcTemplate
				.query(String.format("SELECT * FROM %s WHERE role_id = ?",
						super.tableName), new Object[] { role.getId() }, super.mapper);
	}
}
