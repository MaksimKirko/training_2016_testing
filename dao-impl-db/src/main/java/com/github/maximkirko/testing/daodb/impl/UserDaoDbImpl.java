package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IUserDao;
import com.github.maximkirko.testing.daodb.entitytomap.UserToMap;
import com.github.maximkirko.testing.daodb.mapper.UserMapper;
import com.github.maximkirko.testing.daodb.mapper.UserWithGradesMapper;
import com.github.maximkirko.testing.daodb.mapper.UserWithRoleMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Grade;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

@Repository
public class UserDaoDbImpl extends GenericDaoDbImpl<User, Long> implements IUserDao {

	private String roleTableName;
	private String gradeTableName;
	private UserWithRoleMapper userWithRoleMapper;
	private UserWithGradesMapper userWithGradesMapper;
	
	public UserDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(User.class));
		setEntityToMap(new UserToMap());
		setMapper(new UserMapper());

		roleTableName = DBTableNameAware.getTableNameByClass(Role.class);
		gradeTableName = DBTableNameAware.getTableNameByClass(Grade.class);
		userWithRoleMapper = new UserWithRoleMapper();
		userWithGradesMapper = new UserWithGradesMapper();
	}

	@Override
	public User getWithGrades(Long id) {
		
		User user;
		List<User> users;

		try {
			users = getJdbcTemplate().query(
					String.format(
							"SELECT * FROM %s u LEFT JOIN %s g ON u.id=g.user_id WHERE u.id = ?",
							getTableName(), gradeTableName),
					new Object[] { id }, userWithGradesMapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		if (users.size() < 1) {
			return null;
		}
		user = users.get(0);
		List<Grade> grades = user.getGrades();
		for (User q : users) {
			grades.addAll(q.getGrades());
		}
		user.setGrades(grades);

		return user;
	}

	@Override
	public User getWithRole(Long id) {
		return getJdbcTemplate()
				.queryForObject(String.format("SELECT * FROM %s u LEFT JOIN %s r ON u.role_id=r.id WHERE u.id = ?",
						getTableName(), roleTableName), new Object[] { id }, userWithRoleMapper);
	}

	@Override
	public List<User> getByRole(Role role) {

		return getJdbcTemplate().query(String.format("SELECT * FROM %s WHERE role_id = ?", getTableName()),
				new Object[] { role.getId() }, getMapper());
	}

	@Override
	public User getByEmail(String email) {

		return getJdbcTemplate().queryForObject(String.format("SELECT * FROM %s WHERE email = ?", getTableName()),
				new Object[] { email }, getMapper());
	}

}
