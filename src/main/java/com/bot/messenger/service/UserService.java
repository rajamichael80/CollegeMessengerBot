package com.bot.messenger.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bot.messenger.dao.UserRepository;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.model.fb.UserDetail;

@Service
@Transactional
public class UserService implements IUserService{
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
    UserRepository userRepository;
	
	@Override
	public void saveUser(UserDetail userDetail) {
		System.out.println("-------userRepository-----"+userRepository);

		logger.debug("---inside save user -----");
		User user = new User();
		user.setUserId(1);
		user.setFirstName("A");
		user.setLastName("B");
		User u = userRepository.save(user);
		System.out.println("-----u------"+u);
	}

	@Override
	public List<User> getUserDetails() {
		logger.debug("---inside getUserDetails -----");
		return (List<User>) userRepository.findAll();
	}
	
	

}
