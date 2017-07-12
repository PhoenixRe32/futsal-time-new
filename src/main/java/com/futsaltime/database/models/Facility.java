package com.futsaltime.database.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Facility {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 40, nullable = false, unique = true)
	@NotNull
	private String facilityName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
	private Collection<Bookable> bookables;


	public Facility() {
	}

	public Facility(String facilityName) {
		this.facilityName = facilityName;
	}

	public Long getId() {
		return id;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public Collection<Bookable> getBookables() {
		return bookables;
	}

	public void setBookables(Collection<Bookable> bookables) {
		this.bookables = bookables;
	}
}
