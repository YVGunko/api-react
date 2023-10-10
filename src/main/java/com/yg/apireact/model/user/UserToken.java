package com.yg.apireact.model.user;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserToken {
	public UserToken(Long id, String name, String pswd, String roles, String filial_id) {
		super();
		this.id = id;
		this.name = name;
		this.pswd = pswd;
		this.filial_id = filial_id;
		this.setRoles(roles);
	}
	private Long id;
	@JsonProperty("username")
	private String name;
	@JsonProperty("password")
	private String pswd;
	private String roles;
	private String filial_id;
	
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
	
 	public static UserToken userToUserToken (User b) {
 		return new UserToken(b.getId(), b.getName(), "***", 
 				b.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")),
 				b.getFilial_id().toString());		
 	}
 	public static List<UserToken> userToUserToken (List<User> listUser) {
 		return listUser.stream().map(b -> userToUserToken(b)).collect(Collectors.toList());		
 	}
	public String getFilial_id() {
		return filial_id;
	}
	public void setFilial_id(String filial_id) {
		this.filial_id = filial_id;
	}
}
