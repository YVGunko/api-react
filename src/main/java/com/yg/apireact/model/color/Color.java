package com.yg.apireact.model.color;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yg.apireact.model.division.Division;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "color", indexes= {@Index(name = "indexByNameAndDivision", columnList="name,division_code", unique = true),
		@Index(name = "indexByNameAndDivisionAndOccurrance", columnList="name,division_code,occurrence", unique = true)})
@Entity
public class Color {
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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
	
	public Color(String id) {
		super();
		this.id = id;
		this.name = "";
		this.division = new Division ("0");
	}
	public Color(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.division = new Division ("0");
	}
	
	public Color(String id, String name, String division_code, String occurrence) {
		super();
		this.id = id;
		this.name = name;
		this.occurrence = occurrence;
		this.division = new Division (division_code);
	}
	
	public Color(String id, String name, Division division, String occurrence) {
		super();
		this.id = id;
		this.name = name;
		this.division = division;
		this.occurrence = occurrence;
	}
	
	public Color() {
		super();
	}

	@Id @NotNull private String id;
	@NotNull private String name;

	private String occurrence;

	@ManyToOne(optional = false)
	private Division division ;

	public String getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(division, id, name, occurrence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		return Objects.equals(division, other.division) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(occurrence, other.occurrence);
	}

}
