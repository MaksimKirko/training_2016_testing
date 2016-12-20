package com.github.maximkirko.testing.web.model;

import com.github.maximkirko.testing.datamodel.models.Role.RoleEnum;

public class RoleModel implements WebModel {

	private Long id;
	private RoleEnum type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getType() {
		return type;
	}

	public void setType(RoleEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role [type=" + type + "]";
	}

}
