/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author sosilva
 *
 */
@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	// GET 		/users
	// retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return this.service.findAll();
	}
	
	// GET 		/users/{id}
	// retrieveUser(int id)
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return this.service.findOne(id);
	}
	
	// CREATED
	// input - details of User
	// output - Created & Return the created URI 
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = this.service.save(user);
		
		// Created
		// users/4
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{uri}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}