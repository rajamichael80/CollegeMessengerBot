package com.bot.messenger.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bot.messenger.model.entity.Contact;
import com.bot.messenger.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    //public List<User> findById(Integer userId);
    public List<User> findAll();
    public User save(User c);

}