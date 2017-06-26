package com.futsaltime.facility;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("facilities")
public class FacilityController {

	public FacilityController() {
	}

	@RequestMapping(method = GET, path = "list")
	public Collection<String> listFacilities() {
		return Arrays.asList("Facility A", "Facility B", "Facility C");
	}
}
