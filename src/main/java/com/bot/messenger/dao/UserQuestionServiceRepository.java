package com.bot.messenger.dao;

import org.springframework.data.repository.CrudRepository;

import com.bot.messenger.model.entity.UserQuestions;

public interface UserQuestionServiceRepository extends CrudRepository<UserQuestions, Long> { 

}
