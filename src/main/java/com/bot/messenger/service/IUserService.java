package com.bot.messenger.service;

import java.util.List;

import com.bot.messenger.model.entity.User;
import com.bot.messenger.model.fb.UserDetail;

public interface IUserService {
	
public void saveUser(UserDetail userDetail);

public List<User> getUserDetails();
}
