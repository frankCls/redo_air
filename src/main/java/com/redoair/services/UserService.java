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
		User checkUser = findUserByEmail(user.getEmail());

		if (checkUser != null) {
			System.out.println("old user: " + user.getEmail());
			return userRepository.updateUser(user);
		} else {
			System.out.println("new user: " + user.getFirstName());
			return userRepository.saveUser(user);			
		}
			
	}

	@Override
	public User findUserByUserName(String userName) {	
		return userRepository.findUserByUserName(userName);
	}

	public User findUserByEmail(String email) {		
		return userRepository.findUserByEmail(email);
	}



  
  

}
