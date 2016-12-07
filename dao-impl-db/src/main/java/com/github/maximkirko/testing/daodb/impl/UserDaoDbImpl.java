package com.github.maximkirko.testing.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IUserDao;
import com.github.maximkirko.testing.daodb.entitytomap.UserToMap;
import com.github.maximkirko.testing.daodb.mapper.UserMapper;
import com.github.maximkirko.testing.daodb.mapper.UserWithRoleMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

@Repository
public class UserDaoDbImpl extends GenericDaoDbImpl<User, Long> implements IUserDao {

	private String roleTableName;

	public UserDaoDbImpl() {
		super(User.class);
		super.entityToMap = new UserToMap();
		super.mapper = new UserMapper();
		roleTableName = DBTableNameAware.getTableNameByClass(Role.class);
	}

	@Override
	public User getWithRole(Long id) {
		return jdbcTemplate
				.queryForObject(String.format("SELECT * FROM %s u LEFT JOIN %s r ON u.role_id=r.id WHERE u.id = ?",
						super.tableName, roleTableName), new Object[] { id }, new UserWithRoleMapper());
	}

	@Override
	public List<User> getByRole(Role role) {

		return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE role_id = ?", super.tableName),
				new Object[] { role.getId() }, super.mapper);
	}

	@Override
	public User getByEmail(String email) {
		
		return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE email = ?", super.tableName),
				new Object[] { email }, super.mapper);
	}
}
