package com.yg.apireact.model.customer;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yg.apireact.utils.Utils;


@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	//@Transactional
    public Customer saveOrUpdate(@RequestBody Customer e) throws RuntimeException {
		//check for "new" string is sent as id
		if ( StringUtils.contains(e.getId(), "new") ) {
			e.setId(newCustomerId());
		} else {
			if (customerRepository.findById(e.getId()).isPresent()) {
				Customer oldE = customerRepository.findById(e.getId()).get();

				if (e.getEmail() == null) e.setEmail(oldE.getEmail());
				if (e.getPhone() == null) e.setPhone(oldE.getPhone());
				if (e.getId1c() == null) e.setId1c(oldE.getId1c());
			}
		}
		return customerRepository.save(e);
    }
	
	private String newCustomerId() {
		int exclude[ ] = {};
		int newCustomerId = Utils.getRandomIntWithExclusion (5000, 10000, exclude);

		while (customerRepository.findById(String.valueOf (newCustomerId)).isPresent()) {
			exclude = ArrayUtils.add(exclude, 
					Integer.valueOf(customerRepository.findById(String.valueOf (newCustomerId)).get().getId()));
			newCustomerId = Utils.getRandomIntWithExclusion (5000, 10000, exclude);
		}
		return String.valueOf (newCustomerId);
	}
	

}
