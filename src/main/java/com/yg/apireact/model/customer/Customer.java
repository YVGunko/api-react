package com.yg.apireact.model.customer;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class Customer {
    public Customer() {
		super();
	}
	public Customer(String id) {
		super();
		this.id = id;
		this.name = "";
		this.dt = new Date();
	}
	public Customer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.dt = new Date();
	}
	public Customer(String id, String name, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dt = new Date();
	}
	public Customer(String id, String name, String email, String phone, String id1c) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dt = new Date();
		this.id1c = id1c;
	}
	
	@NotNull @Id private String id;

	@NotNull @Column(name = "name") private String name;

	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	@Column(name = "dt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dt ;
	
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "id1c")
	private String id1c;
	
	private @Version @JsonIgnore Long version;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId1c() {
		return id1c;
	}
	public void setId1c(String id1c) {
		this.id1c = id1c;
	}

	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer client = (Customer) o;
		return Objects.equals(id, client.id) &&
			Objects.equals(name, client.name) &&
			Objects.equals(email, client.email) &&
			Objects.equals(phone, client.phone) &&
			Objects.equals(dt, client.dt) &&
			Objects.equals(id1c, client.id1c) &&
			Objects.equals(version, client.version);
	}
	@Override
	public int hashCode() {

		return Objects.hash(id, name, email, phone, dt, id1c, version);
	}
    @Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", phone='" + phone + '\'' +
			", dt='" + dt +
			", id1c=" + id1c +
			", version=" + version +
			'}';
	}
}
