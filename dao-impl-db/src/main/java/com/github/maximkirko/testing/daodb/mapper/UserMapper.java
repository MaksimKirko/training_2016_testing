package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.User;

public class UserMapper implements IGenericMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setAge(rs.getInt("age"));
		user.setCourse(rs.getString("course"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));

		Role role = new Role();
		role.setId(rs.getLong("role_id"));
		user.setRole(role);

		user.setId(rs.getLong("id"));

		return user;
	}

}
