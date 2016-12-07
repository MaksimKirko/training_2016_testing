package com.github.maximkirko.testing.daoxml.impl;

import java.io.IOException;

import com.github.maximkirko.testing.daoapi.IRoleDao;
import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

public class RoleDaoXmlImpl extends GenericDaoXmlImpl<Role, Long> implements IRoleDao {

	public RoleDaoXmlImpl(Class<Role> entityClass) throws IOException {
		super(entityClass);
	}

	@Override
	public Role getByType(RoleEnum type) {
		// TODO
		throw new UnsupportedOperationException();
	}

}
