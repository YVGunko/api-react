package com.yg.apireact.model.outDoorOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.yg.apireact.model.outDoorOrderRow.OutDoorOrderRowService;

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
@RequestMapping("/api/orders")
public class OutDoorOrderRestController {
	@Autowired OutDoorOrderRepository repo;
	@Autowired OutDoorOrderService service;
	@Autowired OutDoorOrderRowService rowService;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getOrdersPageable(
			@RequestParam(name = "customerId", required = false) String customerId,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

		try {
			Pageable paging = PageRequest.of(page, size);

			Page<OutDoorOrder> pageTuts;
			if (customerId == null) {
				if (userId != null) // TODO check if user valid
					pageTuts = repo.findByUserIdOrderByDateDesc(userId, paging);
				else
					pageTuts = repo.findAllByOrderByDateDesc(paging);
			} else {
				if (userId != null) // TODO check if user valid
					pageTuts = repo.findByClientIdAndUserIdOrderByDateDesc(customerId, userId, paging);
				else
					pageTuts = repo.findByClientIdOrderByDateDesc(customerId, paging);
			}

			List<OutDoorOrderReq> responce = new ArrayList<>();
			for (OutDoorOrder b : pageTuts) {
				// String id, String sDate, String sClient, String sDivision, String sDesc,
				// String sUser
				responce.add(new OutDoorOrderReq(b.getId(), b.getComment(), rowService.getGoods(b.getId()),
						b.getCustomer().getId(), b.getDivision().getCode(), b.getDivision().getName(),
						String.valueOf(b.getUser().getId()), b.getUser().getName(), b.getSample(), b.getDate()));
			}

			Map<String, Object> response = new HashMap<>();
			response.put("orders", responce);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(origins =  {"http://localhost:8082", "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutDoorOrderReq> getOrderById(@PathVariable @NotNull String id) {
		//TODO
		try {
			OutDoorOrder b = repo.findById(id).orElseThrow();
			OutDoorOrderReq response =  new OutDoorOrderReq(b.getId(), b.getComment(), rowService.getGoods(b.getId()),
	   				b.getCustomer().getId(), b.getDivision().getCode(), b.getDivision().getName(),  
	 				String.valueOf(b.getUser().getId()), b.getUser().getName(), 
	 				b.getSample(), b.getDate());
			return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@CrossOrigin(origins =  {"http://localhost:8082", "http://localhost:3000"}, methods = {RequestMethod.POST, 
			RequestMethod.PUT, 
			RequestMethod.PATCH})
	@RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT, 
			RequestMethod.PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<OutDoorOrderReq> SaveOrUpdate(@RequestBody OutDoorOrderReq request) {
		try {
			OutDoorOrderReq response = service.saveOrUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
