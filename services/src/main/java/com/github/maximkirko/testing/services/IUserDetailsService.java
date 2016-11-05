package com.github.maximkirko.testing.services;

import java.util.List;

import com.github.maximkirko.testing.datamodel.users.UserDetails;

public interface IUserDetailsService {

	UserDetails get(Long id);

	List getAll();

	Long save(UserDetails userDetails);

	void saveAll(List<UserDetails> userDetails);

	void delete(Long id);
}
