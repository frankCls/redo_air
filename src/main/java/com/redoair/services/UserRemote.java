package com.redoair.services;

import javax.ejb.Remote;

import com.redoair.domain.Payer;
import com.redoair.domain.User;

@Remote
public interface UserRemote {
	User saveUser(User user);
	User findUserByUserName(String userName);
}
