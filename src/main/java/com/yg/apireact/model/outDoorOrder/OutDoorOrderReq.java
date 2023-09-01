package com.yg.apireact.model.outDoorOrder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OutDoorOrderReq {

	@JsonProperty("id")
	public String id;
	
	@JsonProperty("comment")
	public String comment;
	@JsonProperty("details")
	public String details;
	
	@JsonProperty("customer_id")
	public String customer_id ;

	@JsonProperty("division_code")
	public String division_code ;	
	@JsonProperty("division_name")
	public String division_name ;
	
	@JsonProperty("user_id")
	public String user_id ;
	@JsonProperty("user_name")
	public String user_name ;
	
	@JsonProperty("sample")
	Boolean sample;
	
	@JsonProperty("date")
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	Date date ;
	
 	public static OutDoorOrderReq outDoorOrderToOutDoorOrderReq (OutDoorOrder b) {
 		return new OutDoorOrderReq(b.getId(), b.getComment(), "",
 				b.getCustomer().getId(), b.getDivision().getCode(), b.getDivision().getName(),  
 				String.valueOf(b.getUser().getId()), b.getUser().getName(), 
 				b.getSample(), b.getDate());		
 	}
 	public static List<OutDoorOrderReq> outDoorOrderToOutDoorOrderReq (List<OutDoorOrder> listOfOutDoorOrder) {
 		return listOfOutDoorOrder.stream().map(b -> outDoorOrderToOutDoorOrderReq(b)).collect(Collectors.toList());		
 	}
 	
	public OutDoorOrderReq() {
		super();
	}

	public OutDoorOrderReq(String id, String comment, String details, String customer_id, String division_code,
			String division_name, String user_id, String user_name, Boolean sample, Date date) {
		super();
		this.id = id;
		this.comment = comment;
		this.details = details;
		this.customer_id = customer_id;
		this.division_code = division_code;
		this.division_name = division_name;
		this.user_id = user_id;
		this.user_name = user_name;
		this.sample = sample;
		this.date = date;
	}
	
	public OutDoorOrderReq(String id, String comment, String user_id, String customer_id, String division_code, Boolean sample) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = "";
		this.comment = comment;
		this.date = new Date();
		this.division_code = division_code;
		this.division_name = "";
		this.customer_id = customer_id;
		this.sample = sample;
	}
	
	public Boolean getSample() {
		return sample;
	}

	public void setSample(Boolean sample) {
		this.sample = sample;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDivision_code() {
		return division_code;
	}
	public void setDivision_code(String division_code) {
		this.division_code = division_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
