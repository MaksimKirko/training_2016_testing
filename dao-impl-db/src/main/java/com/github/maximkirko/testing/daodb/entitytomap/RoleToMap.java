package com.github.maximkirko.testing.daodb.entitytomap;

import java.util.HashMap;
import java.util.Map;

import com.github.maximkirko.testing.datamodel.models.Role;

public class RoleToMap implements IEntityToMap<Role> {
	
	@Override
	public Map<String, Object> convert(Role entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", entity.getType());
		params.put("id", entity.getId());
		
		return params;
	}

}
