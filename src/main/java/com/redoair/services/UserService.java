package com.redoair.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.User;
import com.redoair.repositories.UserRepository;

@Stateless
@LocalBean
public class UserService implements UserRemote {

	@Inject
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public User findUserByUserName(String userName) {
	
		return userRepository.findUserByUserName(userName);
	}



  
  

}
