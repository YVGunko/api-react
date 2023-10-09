package com.yg.apireact.model.outDoorOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.yg.apireact.model.customer.CustomerRepository;
import com.yg.apireact.model.outDoorOrderRow.OutDoorOrderRowService;
import com.yg.apireact.model.user.UserRepository;
import com.yg.apireact.utils.Utils;

@RestController
//we allow cors requests from our frontend environment
//note the curly braces that creates an array of strings ... required by the annotation
@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
		RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH,
		RequestMethod.HEAD })
@RequestMapping("/api/orders")
public class OutDoorOrderRestController {
	private static final int DETAILS_LENGTH = 25;

	@Autowired
	OutDoorOrderRepository repo;
	@Autowired
	OutDoorOrderService service;
	@Autowired
	OutDoorOrderRowService rowService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CustomerRepository clientRepository;

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OutDoorOrderReq>> getOrders(@RequestParam(name = "userId", required = false) Long userId,
			@RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateFrom,
			@RequestParam(name = "dateTill", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateTill) {

		try {
			List<OutDoorOrderReq> responce = new ArrayList<>();
			List<OutDoorOrder> pageTuts = new ArrayList<>();
			if (dateFrom == null)
				dateFrom = Utils.toDate(Utils.startOfMonth());
			if (dateTill == null)
				dateTill = Utils.toDate(Utils.endOfMonth());
			if (userId != null) {
				userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException(
						"userId not found exception. userId=".concat(userId.toString())));
				pageTuts = repo.findByUserIdAndDateBetweenOrderByDateDesc(userId, dateFrom, dateTill).orElseThrow();
			} else {
				pageTuts = repo.findByDateBetweenOrderByDateDesc(dateFrom, dateTill).orElseThrow();
			}

			for (OutDoorOrder b : pageTuts) {
				// String id, String sDate, String sClient, String sDivision, String sDesc,
				// String sUser
				responce.add(new OutDoorOrderReq(b.getId(), b.getComment(),
						rowService.getGoods(b.getId(), DETAILS_LENGTH), b.getCustomer().getId(),
						b.getCustomer().getName(), b.getDivision().getCode(), b.getDivision().getName(),
						String.valueOf(b.getUser().getId()), b.getUser().getName(), b.getSample(), b.getDate()));
			}

			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.GET,
			RequestMethod.OPTIONS })
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutDoorOrderReq> getOrderById(@PathVariable @NotNull String id) {
		// TODO
		try {
			OutDoorOrder b = repo.findById(id).orElseThrow();
			OutDoorOrderReq response = new OutDoorOrderReq(b.getId(), b.getComment(),
					rowService.getGoods(b.getId(), DETAILS_LENGTH), b.getCustomer().getId(), b.getCustomer().getName(),
					b.getDivision().getCode(), b.getDivision().getName(), String.valueOf(b.getUser().getId()),
					b.getUser().getName(), b.getSample(), b.getDate());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.POST })
	@RequestMapping(value = "", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutDoorOrderReq> post(@RequestBody OutDoorOrderReq request) {
		try {
			OutDoorOrderReq response = service.saveOrUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.POST })
	@RequestMapping(value = "/copy", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutDoorOrderReq> copy(@RequestBody OutDoorOrderReq request) {
		try {
			String savedId = request.getId();
			//have to make new instance in order to be saved by hibernate
			OutDoorOrderReq response = service.copy(request);
			// get rows by saveId and make them copy having orderId replaced with
			// response.getId();
			rowService.copyRows(savedId, OutDoorOrder.orderReqToOrder(response));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.PUT,
			RequestMethod.PATCH })
	@RequestMapping(value = "{id}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<OutDoorOrderReq> putPatch(@RequestBody OutDoorOrderReq request) {
		try {
			OutDoorOrderReq response = service.saveOrUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://localhost:8082", "http://localhost:3000" }, methods = { RequestMethod.POST })
	@RequestMapping(value = "sendMail", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendOrder(@RequestParam String id) throws Exception {
		try {
			// TODO remove !!! service.sendMail(id);
			JsonObject answer = Json.object().add("answer", "Ok");

			return new ResponseEntity<String>(answer.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
