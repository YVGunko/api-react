package com.yg.apireact.model.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
		RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH,
		RequestMethod.HEAD })
@RequestMapping("/api/customers")
public class CustomerRestController {
	private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerRepository customerRepository;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getAllTutorials(
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

		try {
			// List<Customer> tutorials = new ArrayList<Customer>();
			Pageable paging = PageRequest.of(page, size);

			Page<Customer> pageTuts;
			if (title == null)
				pageTuts = customerRepository.findAllByOrderByName(paging);
			else
				pageTuts = customerRepository.findByNameContainingOrderByName(title, paging);

			List<Customer> cus = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("customers", cus);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(
					customerRepository.findById(id).orElseThrow(
							() -> new NoSuchElementException("Customer not found exception. id=".concat(id))),
					HttpStatus.OK);
		} catch (NoSuchElementException e) {
			log.error("Customer not found exception. id=".concat(id));
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.DELETE,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			customerRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.POST,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
		HttpHeaders headers = new HttpHeaders();

		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			return new ResponseEntity<>(this.customerService.saveOrUpdate(customer), headers, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.PUT,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> patchCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();

		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			return new ResponseEntity<>(this.customerService.saveOrUpdate(customer), headers, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
