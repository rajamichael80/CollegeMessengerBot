package com.bot.messenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.messenger.dao.UserRepository;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.model.fb.UserDetail;

public class UserService implements IUserService{
	@Autowired
    UserRepository userRepository;
	
	@Override
	public void saveUser(UserDetail userDetail) {
		User user = new User();
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		userRepository.save(user);
	}

	@Override
	public List<User> getUserDetails() {
		return userRepository.findAll();
	}
	
	

}
