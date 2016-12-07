package com.github.maximkirko.testing.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.maximkirko.testing.datamodel.models.Role;

public class RoleMapper implements IGenericMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int i) throws SQLException {
		Role role = new Role();
		role.setId(rs.getLong("id"));
		role.setType(Role.RoleEnum.valueOf(rs.getString("type")));

		return role;
	}

}
