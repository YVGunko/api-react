package com.yg.apireact.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserToken {
	public UserToken(Long id, String name, String pswd, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.pswd = pswd;
		this.setRoles(roles);
	}
	private Long id;
	@JsonProperty("username")
	private String name;
	@JsonProperty("password")
	private String pswd;
	private String roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
}