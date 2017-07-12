package com.futsaltime.database.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Bookable {

	@Id @GeneratedValue
	private Long id;

	@Column(nullable = false)
	@NotNull
	private int capacity;

	@ManyToOne
	@JoinColumn(name = "facility_id")
	private Facility facility;

	public Bookable() {
	}

	public Bookable(int capacity, Facility facility) {
		this.capacity = capacity;
		this.facility = facility;
	}

	public Long getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}
}
