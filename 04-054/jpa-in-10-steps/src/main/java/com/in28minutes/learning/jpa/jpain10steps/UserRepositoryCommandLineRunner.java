package com.in28minutes.learning.jpa.jpain10steps;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;
import com.in28minutes.learning.jpa.jpain10steps.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User("Jill", "Admin");
		this.userRepository.save(user);
		
		Optional<User> userWithId1 = this.userRepository.findById(1L);
		
//		LOG.info("New User is created : " + insert);
//		LOG.info("New User is created : " + user);
		
		LOG.info("User is retrieved : " + userWithId1	);
		
		List<User> users = this.userRepository.findAll();
		
		LOG.info("All users : " + users); 
		
	}

}
