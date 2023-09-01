package com.yg.apireact.model.user;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yg.apireact.Constants;
import com.yg.apireact.model.customer.Customer;
import com.yg.apireact.model.customer.CustomerRestController;

@RestController
@CrossOrigin(
		origins =  {Constants.ORIGINS}, 
		methods = {RequestMethod.GET, 
				RequestMethod.OPTIONS,
				RequestMethod.POST, 
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.PATCH,
				RequestMethod.HEAD})
@RequestMapping("/test/user")
public class UserRestController {
	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@CrossOrigin(origins =  {Constants.ORIGINS}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getOneById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(userRepository.findById(id)
					.orElseThrow(() -> new NoSuchElementException("User not found exception. id=".concat(String.valueOf(id)))),
					HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	    	log.error("User not found exception. id=".concat(String.valueOf(id)));
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	}
}
