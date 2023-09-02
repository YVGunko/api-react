package com.yg.apireact.model.filial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yg.apireact.Constants;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(
		origins =  {"http://localhost:8082", "http://localhost:3000"}, 
		methods = {RequestMethod.GET, 
				RequestMethod.OPTIONS,
				RequestMethod.POST, 
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.PATCH,
				RequestMethod.HEAD})
@RequestMapping("/api/filials")
public class FilialRestController {
	@Autowired FilialRepository repo;
	
	@CrossOrigin(origins =  {"http://localhost:8082", "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filial>> getFilials() {
	
	    try {	
	      Iterable<Filial> iterable = repo.findAll();
	      List<Filial> response = Streamable.of(iterable)
	              .toList();
	
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
