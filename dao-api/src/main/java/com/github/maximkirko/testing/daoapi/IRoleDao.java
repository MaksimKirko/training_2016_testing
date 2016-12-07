package com.github.maximkirko.testing.daoapi;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

public interface IRoleDao extends IGenericDao<Role, Long> {
	
	Role getByType(RoleEnum type);
}
