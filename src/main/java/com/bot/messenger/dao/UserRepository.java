package com.bot.messenger.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bot.messenger.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}