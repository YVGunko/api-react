package com.yg.apireact.model.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yg.apireact.Constants;

@RestController
@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
		RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH,
		RequestMethod.HEAD })
@RequestMapping("/api/users")
public class UserRestController {
	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	UserRepository userRepository;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserToken>> getUsers() {

		try {
			Iterable<User> iterableU = userRepository.findAll();
			if (iterableU == null) {
				log.debug("UserRestController -> getUsers -> No users found is null");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<User> listU = Streamable.of(iterableU)
					.filter(user -> (user.getRoles().stream()
							.filter(role -> (role.getName().contains(Constants.ORDER_MAKER))).count()>0))
					.toList();
			List<UserToken> response = UserToken.userToUserToken(listU);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getOneById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(userRepository.findById(id).orElseThrow(
					() -> new NoSuchElementException("User not found exception. id=".concat(String.valueOf(id)))),
					HttpStatus.OK);
		} catch (NoSuchElementException e) {
			log.error("User not found exception. id=".concat(String.valueOf(id)));
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
