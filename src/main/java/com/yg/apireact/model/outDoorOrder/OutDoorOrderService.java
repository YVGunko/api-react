package com.yg.apireact.model.outDoorOrder;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.apireact.model.customer.Customer;
import com.yg.apireact.model.customer.CustomerRepository;
import com.yg.apireact.model.division.Division;
import com.yg.apireact.model.division.DivisionRepository;
import com.yg.apireact.model.user.User;
import com.yg.apireact.model.user.UserRepository;

@Service
public class OutDoorOrderService {
	@Autowired
	OutDoorOrderRepository repo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	DivisionRepository divisionRepo;
	@Autowired
	UserRepository userRepo;
	
	public String getNextOrderNumber (String client_id)  throws Exception {
		String responce = null;
		if (client_id != null) {
			try {
					Long l = repo.getNextOrderNumber(client_id)+1L;
					responce = client_id+"-" + l;
			}catch (Exception e){
					responce = client_id+"-"+1;
			}
		}
		return responce;
	}
	
	public OutDoorOrderReq saveOrUpdate(OutDoorOrderReq request)  throws Exception {

		Customer customer = customerRepo.findById(request.customer_id).orElseThrow();
		User user = userRepo.findById(Long.valueOf(request.user_id)).orElseThrow();
		Division division = divisionRepo.findById(request.division_code).orElseThrow();
		
		String orderId = StringUtils.isBlank(request.id) 
				? getNextOrderNumber(request.customer_id) 
						: request.id;
		
		OutDoorOrder tmp = repo.save(new OutDoorOrder(orderId, request.comment, 
				(request.date != null ? request.date : new Date()), division, user, 
				customer, request.sample));	
		OutDoorOrderReq responce = OutDoorOrderReq.outDoorOrderToOutDoorOrderReq(tmp) ;
	
		return responce;
	}
	
}
