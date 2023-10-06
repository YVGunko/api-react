package com.yg.apireact.model.defaults;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Defaults {
	public Defaults() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Defaults(@NotNull String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Id
	@JsonProperty("id")
	@Access(AccessType.PROPERTY)
	@NotNull
	public String id;
	@JsonProperty("name")
	public String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static int getIntegerValueOrZero(String name) {
		Integer result = 0;
		try {
			result = Integer.valueOf(name);
		} catch (NumberFormatException e) {
			result = 0;
		}
		
		return result;
	}
}
