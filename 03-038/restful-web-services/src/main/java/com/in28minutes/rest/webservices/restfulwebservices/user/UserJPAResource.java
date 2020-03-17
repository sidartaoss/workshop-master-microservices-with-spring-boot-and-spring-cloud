/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

//	@Autowired
//	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// GET 		/users
	// retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
//		return this.service.findAll();
		return this.userRepository.findAll();
	}
	
	// GET 		/users/{id}
	// retrieveUser(int id)
	@GetMapping("/jpa/users/{id}")
//	public User retrieveUser(@PathVariable int id) {
	public Resource<User> retrieve(@PathVariable int id) {
//		User user = this.service.findOne(id);
		Optional<User> user = this.userRepository.findById(id);
		
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		// HATEOAS
		
		// "all-users",			SERVER_PATH + "/users"
		// retrieveAllUsers
//		Resource<User> resource = new Resource(user);
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
 		resource.add(linkTo.withRel("all-users"));
		
//		return user;
		return resource;
	}
	
	// CREATED
	// input - details of User
	// output - Created & Return the created URI 
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//		User savedUser = this.service.save(user);
		User savedUser = this.userRepository.save(user);
		
		// Created
		// users/4
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{uri}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
//		User savedUser = this.userRepository.save(user);
		Optional<User> optionalUser = this.userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		User user = optionalUser.get();
		
		post.setUser(user);
		
		Post savedPost = this.postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{uri}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
//		User user = this.service.deleteById(id);
//		User user = this.userRepository.deleteById(id);
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
		this.userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return optionalUser.get().getPosts();
	}
	
}