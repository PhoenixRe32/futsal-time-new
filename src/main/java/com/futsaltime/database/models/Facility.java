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

	@Override
	public String toString() {
		return "Facility No " + id + ": " + facilityName;
	}
}
