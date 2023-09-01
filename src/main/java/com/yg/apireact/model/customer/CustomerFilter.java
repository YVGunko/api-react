package com.yg.apireact.model.customer;

public class CustomerFilter {
	public CustomerFilter(String name) {
		super();
		this.name = name;
	}
	public CustomerFilter() {}
	private String name ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
