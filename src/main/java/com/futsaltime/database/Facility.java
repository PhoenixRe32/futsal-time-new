package com.futsaltime.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Facility {

	@Getter @Setter
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Getter @Setter
	@Column(length = 40, nullable = false)
	@NotNull
	private String facilityName;
}
