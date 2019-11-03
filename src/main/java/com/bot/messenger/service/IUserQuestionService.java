package com.bot.messenger.service;

import java.util.List;

import com.bot.messenger.model.entity.UserQuestions;

public interface IUserQuestionService {
	public void saveUser(UserQuestions userDetail);
    public List<UserQuestions> getUserQuestions();
    public void deleteUserQuestion(Long id);
}
