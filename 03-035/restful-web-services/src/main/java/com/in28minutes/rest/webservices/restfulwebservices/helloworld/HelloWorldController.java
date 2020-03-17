/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sosilva
 *
 */
// Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

		// GET
		// URI		- /hello-world
		// method - "HelloWorld"

//		@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
		@GetMapping(path = "/hello-world")
		public String helloWorld() {
			return "Hello World";
		}
		
		// @GetMapping(path = "/hello-world")
		@GetMapping(path = "/hello-world-bean")
//		public String helloWorldBean() {
		public HelloWorldBean helloWorldBean() {
//			return "Hello World";
			return new HelloWorldBean("Hello World");
			
		}
		
		//		/hello-world/path-variable/in28minutes
		@GetMapping(path = "/hello-world/path-variable/{name}")
		public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
			return new HelloWorldBean(String.format("Hello World, %s", name));
		}
		
		@GetMapping(path = "/hello-world-internationalized")
		public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false)  Locale locale) {
//			return "Good Morning";
//			return this.messageSource.getMessage("good.morning.message", null, locale);
			return this.messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
		}		
}
