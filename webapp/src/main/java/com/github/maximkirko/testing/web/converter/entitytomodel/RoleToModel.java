package com.github.maximkirko.testing.web.converter.entitytomodel;

import org.springframework.core.convert.converter.Converter;

import com.github.maximkirko.testing.datamodel.models.Role;
import com.github.maximkirko.testing.web.model.RoleModel;

public class RoleToModel implements Converter<Role, RoleModel> {

	@Override
	public RoleModel convert(Role role) {

		RoleModel model = new RoleModel();
		model.setId(role.getId());
		model.setType(role.getType());
		return model;
	}

}
