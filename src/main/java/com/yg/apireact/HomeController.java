package com.yg.apireact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

//@CrossOrigin(origins =  {"http://localhost:4232/"})
@Controller
public class HomeController {

	@RequestMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}

}

