package com.yg.apireact.model.product;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
		RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH,
		RequestMethod.HEAD })
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	ProductRepository repo;
	@Autowired
	ProductService service;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductReq>> getAll(
			@RequestParam(name = "division_code", required = true) String divisionCode) {
		return service.find(divisionCode, null);
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductReq> getById(@PathVariable @NotNull String id) {
		return service.find(id);
	}
}
