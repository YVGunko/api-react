package com.yg.apireact.model.product;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yg.apireact.Constants;


@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(
		origins =  {Constants.ORIGINS}, 
		methods = {RequestMethod.GET, 
				RequestMethod.OPTIONS,
				RequestMethod.POST, 
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.PATCH,
				RequestMethod.HEAD})
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	ProductRepository repo;
	@Autowired
	ProductService service;
	
	@CrossOrigin(origins =  {Constants.ORIGINS}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@RequestMapping(value = "/v100", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getTop100Products(
			@RequestParam(name = "division_code", required = true) String divisionCode,
			@RequestParam(name = "product_name", required = false) String productName) {

			return service.find (divisionCode, productName);
	
	}
	@CrossOrigin(origins =  {Constants.ORIGINS}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAll(
			@RequestParam(name = "division_code", required = true) String divisionCode) {

			return service.find (divisionCode, null);
	
	}
}
