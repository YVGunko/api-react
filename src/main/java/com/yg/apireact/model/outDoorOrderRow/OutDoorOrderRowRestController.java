package com.yg.apireact.model.outDoorOrderRow;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.yg.apireact.model.outDoorOrder.OutDoorOrderService;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
		RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH,
		RequestMethod.HEAD })
@RequestMapping("/api/orderRows")
public class OutDoorOrderRowRestController {
	@Autowired
	OutDoorOrderRowRepository repo;
	@Autowired
	OutDoorOrderRowService service;
	@Autowired
	OutDoorOrderService orderService;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OutDoorOrderRowReq>> getRowListByOrderId(@RequestParam(name = "orderId", required = true) String orderId) {
		try {
			return new ResponseEntity<>(OutDoorOrderRowReq.rowToRowReq(repo.findByOutDoorOrderIdOrderByDtDesc(orderId).orElseThrow()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutDoorOrderRowReq> getById(@PathVariable @NotNull String id) {
		try {
			return new ResponseEntity<>(OutDoorOrderRowReq.rowToRowReq(repo.findById(id).orElseThrow()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.POST })
	@RequestMapping(value = "", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<OutDoorOrderRowReq> post(@RequestBody OutDoorOrderRowReq request) {
		try {
			OutDoorOrderRowReq response = service.saveOrUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.PUT,
			RequestMethod.PATCH })
	@RequestMapping(value = "{id}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<OutDoorOrderRowReq> putPatch(@RequestBody OutDoorOrderRowReq request) {
		try {
			OutDoorOrderRowReq response = service.saveOrUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
