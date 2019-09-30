package com.bot.messenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.messenger.dao.UserQuestionServiceRepository;
import com.bot.messenger.model.entity.UserQuestions;
@Service
public class UserQuestionService implements IUserQuestionService{
	@Autowired
    UserQuestionServiceRepository UserQuestionServiceRepository;
	
	@Override
	public void saveUser(UserQuestions userQuestions) {
		System.out.println("===inside savUserQuestions===="+UserQuestionServiceRepository);

		UserQuestions u = UserQuestionServiceRepository.save(userQuestions);
		System.out.println("===UserQuestions===="+u);

	}

	@Override
	public List<UserQuestions> getUserQuestions() {
		System.out.println("===inside getUserQuestions===="+UserQuestionServiceRepository);

		List<UserQuestions> userQuestions =  (List<UserQuestions>) UserQuestionServiceRepository.findAll();
		System.out.println("---userQuestions---"+userQuestions);
		return null;
	}

}
