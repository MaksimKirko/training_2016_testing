package com.github.maximkirko.testing.web.converter.modeltoentity;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.web.model.RoleModel;

public class ModelToRole implements Converter<RoleModel, Role> {

	@Override
	public Role convert(RoleModel model) {

		Role role = new Role();
		role.setId(model.getId());
		role.setType(model.getType());
		return role;
	}

}
