package com.bot.messenger.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.messenger.dao.UserRepository;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.model.fb.UserDetail;

@Service
//@Transactional
public class UserService implements IUserService{
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
    UserRepository userRepository;
	@Autowired
    private EntityManager manager;

	@Override
	public void saveUser(UserDetail userDetail) {
		System.out.println("-------userRepository-----"+userRepository);

		logger.debug("---inside save user -----");
		User user = new User();
		//user.setId(1L);
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setSenderId(userDetail.getId());
		List<User> users = findBySenderId(userDetail.getId());
		if(users.size()==0) {
		User u = userRepository.save(user);
		System.out.println("-----u------"+u);
		}
	}

	@Override
	public List<User> getUserDetails() {
		logger.debug("---inside getUserDetails -----");
		List<User> users =  (List<User>) userRepository.findAll();
		System.out.println("---users---"+users);
		return users;
	}
	
	public List<User> findBySenderId(String senderId){
		String sql = "Select * from users where senderid=:senderId";
		Query query = manager.createNativeQuery(sql);
		query.setParameter("senderId", senderId);
		List<User> users = (List<User>)query.getResultList();
		System.out.println("----users--->"+users);
		return users;
		
	}

	

}
