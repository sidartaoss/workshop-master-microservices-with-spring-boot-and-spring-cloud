/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
//	public User retrieveUser(@PathVariable int id) {
	public Resource<User> retrieve(@PathVariable int id) {
		User user = this.service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		// HATEOAS
		
		// "all-users",			SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
 		resource.add(linkTo.withRel("all-users"));
		
//		return user;
		return resource;
	}
	
	// CREATED
	// input - details of User
	// output - Created & Return the created URI 
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
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
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = this.service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}	
	
}
