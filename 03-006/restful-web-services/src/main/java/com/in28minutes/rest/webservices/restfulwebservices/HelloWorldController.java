/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SEMPR
 *
 */
// Controller
@RestController
public class HelloWorldController {

	// GET
	// URI - /hello-world
	// method - "Hello World"

//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// hello-world-bean
	@GetMapping(path = "/hello-world-bean")
//	public String helloWorldBean() {
	public HelloWorldBean helloWorldBean() {
//		return "Hello World";
		return new HelloWorldBean("Hello World");
	}

}
