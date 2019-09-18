package com.bot.messenger.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bot.messenger.model.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    public List<Contact> findById(Integer id);
    public List<Contact> findAll();
    public Contact save(Contact c);

}