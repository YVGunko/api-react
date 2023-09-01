package com.yg.apireact.model.customer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = {CustomerController.PATH,CustomerController.PATH_PAGENATED})

@Controller
public class CustomerController {
	protected static final String PATH = "/order/edit/customers";
	protected static final String PATH_PAGENATED = "/order/edit/customers/page/{page-number}";
	
	public static CustomerFilter customerFilter;
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping
	public ModelAndView get(@RequestParam(value = "page", required = false) Integer pageNumber,
			@ModelAttribute("customerFilter") CustomerFilter customerFilter) throws ParseException {
		
		ServletRequestAttributes attributes = 
		        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attributes.getRequest().getSession(true);
		
		if (pageNumber == null) pageNumber = 1;
		
		return mavPost(pageNumber, customerFilter);
		//return mavPost(pageNumber, "", customerFilter);
	}
	
	/*@RequestMapping(value={"/newCustomer"}, method=RequestMethod.POST)
	public ModelAndView mavOpen(@RequestParam(value = "page", required = false) Integer page,
			@ModelAttribute("customerFilter") CustomerFilter customerFilter) throws ParseException {
		
			ModelAndView mv = new ModelAndView();
			return mv;
	}*/
	
	//@RequestMapping(value={""}, method=RequestMethod.POST, params={"action"})
	//@PostMapping(params={"filter"})
	@PostMapping(params={"action=filter"})
	public ModelAndView mavPost(@RequestParam(value = "page", required = false, defaultValue = "1" ) Integer page,
			//@ModelAttribute ("customer") List<Customer> customer, 
	        //@RequestParam(value="action", required=true) String action,
				@ModelAttribute("customerFilter") CustomerFilter customerFilter) throws ParseException {
		
		ModelAndView mv = new ModelAndView();
		
		try {
		      //TODO size !
		      Pageable paging = PageRequest.of(page - 1, 10);
		      
		      Page<Customer> pageTuts;
		      if (StringUtils.isEmpty(customerFilter.getName()))
		        pageTuts = customerRepository.findAll(paging);
		      else
		        pageTuts = customerRepository.findByNameContainingOrderByName(customerFilter.getName(), paging);
		
		      List<Customer> cus = pageTuts.getContent();
		
			ServletRequestAttributes attributes = 
			        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attributes.getRequest().getSession(true);
			session.setAttribute("customerFilter",
					String.valueOf(customerFilter));
			
			mv.addObject("currentPage", page);
			mv.addObject("totalPages", pageTuts.getTotalPages());
			mv.addObject("totalItems", pageTuts.getTotalElements());
			
			if (customerFilter.getName() == null) customerFilter.setName("");
			mv.addObject("customerFilter", customerFilter);
			mv.addObject("customer", cus);
			
		} catch (Exception e) {
			if (customerFilter.getName() == null) customerFilter.setName("");
			mv.addObject("currentPage", 1);
			mv.addObject("totalPages", 0);
			mv.addObject("totalItems", 0l);
			mv.addObject("customerFilter", customerFilter);
			mv.addObject("customer", new ArrayList<>());
		}
		mv.setViewName("customers");
		return mv;
	}

}
