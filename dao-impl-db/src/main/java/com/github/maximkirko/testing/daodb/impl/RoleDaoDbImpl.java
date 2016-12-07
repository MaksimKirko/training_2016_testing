package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IRoleDao;
import com.github.maximkirko.testing.daodb.entitytomap.RoleToMap;
import com.github.maximkirko.testing.daodb.mapper.RoleMapper;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

@Repository
public class RoleDaoDbImpl extends GenericDaoDbImpl<Role, Long> implements IRoleDao {

	public RoleDaoDbImpl() {
		super(Role.class);
		super.entityToMap = new RoleToMap();
		super.mapper = new RoleMapper();
	}

	@Override
	public Role getByType(RoleEnum type) {
		
		return jdbcTemplate.queryForObject(String.format("SELECT * FROM %s WHERE type = '%s'", super.tableName, type.toString()), super.mapper);
	}

}
