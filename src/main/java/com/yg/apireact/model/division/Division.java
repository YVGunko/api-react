package com.yg.apireact.model.division;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity

public class Division {
	@Access(AccessType.PROPERTY)
	@Id
	String code;

	@NotNull
	String name;
	
	public Division() {
		super();
	}
	public Division(String division_code) {
		super();
		this.code = division_code;
		this.name = "";
	}
	public Division(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Division other = (Division) obj;
		return Objects.equals(code, other.code) && Objects.equals(name, other.name);
	}
}
