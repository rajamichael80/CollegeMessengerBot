package com.bot.messenger.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.messenger.dao.UserQuestionServiceRepository;
import com.bot.messenger.model.entity.UserQuestions;

public class UserQuestionService implements IUserQuestionService{
	@Autowired
    UserQuestionServiceRepository UserQuestionServiceRepository;
	@Autowired
    private EntityManager manager;
	@Override
	public void saveUser(UserQuestions userQuestions) {
		UserQuestions u = UserQuestionServiceRepository.save(userQuestions);
		System.out.println("===UserQuestions===="+u);

	}

	@Override
	public List<UserQuestions> getUserDetails() {
		List<UserQuestions> userQuestions =  (List<UserQuestions>) UserQuestionServiceRepository.findAll();
		System.out.println("---userQuestions---"+userQuestions);
		return null;
	}

}
