package com.github.maximkirko.testing.services;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

public interface IRoleService extends IGenericService<Role, Long> {

	Role getByType(RoleEnum type);
}
