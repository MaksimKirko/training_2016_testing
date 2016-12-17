package com.github.maximkirko.testing.daodb.impl;

import org.springframework.stereotype.Repository;

import com.github.maximkirko.testing.daoapi.IRoleDao;
import com.github.maximkirko.testing.daodb.entitytomap.RoleToMap;
import com.github.maximkirko.testing.daodb.mapper.RoleMapper;
import com.github.maximkirko.testing.datamodel.annotations.anylizer.DBTableNameAware;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

@Repository
public class RoleDaoDbImpl extends GenericDaoDbImpl<Role, Long> implements IRoleDao {

	public RoleDaoDbImpl() {
		super();
		setTableName(DBTableNameAware.getTableNameByClass(Role.class));
		setEntityToMap(new RoleToMap());
		setMapper(new RoleMapper());
	}

	@Override
	public Role getByType(RoleEnum type) {

		return getJdbcTemplate().queryForObject(
				String.format("SELECT * FROM %s WHERE type='%s'", getTableName(), type.toString()), getMapper());
	}

}
