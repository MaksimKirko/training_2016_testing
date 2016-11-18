package com.github.maximkirko.testing.daodb.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IStudentDao;
import com.github.maximkirko.testing.daodb.mapper.StudentMapper;
import com.github.maximkirko.testing.daodb.mapper.StudentWithRoleMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Student;

@Repository
public class StudentDaoDbImpl extends GenericDaoDbImpl<Student, Long> implements IStudentDao {
	
	private String roleTableName;
	
	public StudentDaoDbImpl() {
		super(Student.class, new StudentMapper());
		roleTableName = DBTableNameAware.getTableNameByClass(Role.class);
	}

	@Override
	public Map<String, Object> entityToMap(Student entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("first_name", entity.getFirstName());
		params.put("last_name", entity.getLastName());
		params.put("age", entity.getAge());
		params.put("course", entity.getCourse());
		params.put("email", entity.getEmail());
		params.put("password", entity.getPassword());
		params.put("id", entity.getId());
		
		return params;
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
