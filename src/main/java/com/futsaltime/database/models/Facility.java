package com.futsaltime.database.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Facility {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 40, nullable = false, unique = true)
	@NotNull
	private String facilityName;

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
}
