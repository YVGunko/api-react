package com.yg.apireact;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yg.apireact.model.filial.FilialService;
import com.yg.apireact.model.user.Role;
import com.yg.apireact.model.user.User;
import com.yg.apireact.model.user.UserRepository;
import com.yg.apireact.model.user.UserToken;

@RestController
@CrossOrigin(
		origins =  {"http://localhost:8082", "http://localhost:3000"}, 
		methods = {RequestMethod.GET, 
				RequestMethod.OPTIONS,
				RequestMethod.POST, 
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.PATCH,
				RequestMethod.HEAD})
@RequestMapping("/api/login")
public class LoginController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	FilialService fservice;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@CrossOrigin(origins =  {"http://localhost:8082", "http://localhost:3000"}, methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserToken> basicLogin(@RequestBody @Valid UserToken userToken) {
        HttpHeaders headers = new HttpHeaders();

        if (userToken == null) {
        	log.debug("userToken is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
        	
        	User user = this.userRepository.findOneByNameAndPswdAndExternalTrue(userToken.getName(), userToken.getPswd());
			if (user == null) {
				log.debug("LoginController -> basicLogin -> No user found.");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
        	userToken.setId(user.getId());
        	userToken.setPswd("***");
        	userToken.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")));
        	userToken.setFilial_id(user.getFilial_id());
        	userToken.setFilial(fservice.getName(user.getFilial_id()));
        	return new ResponseEntity<>(userToken, headers, HttpStatus.CREATED);
        } catch (RuntimeException e) {
        	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
	}
}
