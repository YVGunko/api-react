package com.yg.apireact.model.filial;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Filial {

	@NotNull 
	@Id 
	@JsonProperty("filial_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@NotNull 
	@Column(name = "name") 
	@JsonProperty("filial_name") 
	private String name;
	
	public Filial() {
		super();
	}
	public Filial(@NotNull Long id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Filial f = (Filial) o;
		return Objects.equals(id, f.id) &&
			Objects.equals(name, f.name) ;
	}
	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}
    @Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
