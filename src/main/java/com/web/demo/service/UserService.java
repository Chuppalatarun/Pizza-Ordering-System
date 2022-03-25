package com.web.demo.service;

import com.web.demo.model.User;

public interface UserService {
	
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	
}
