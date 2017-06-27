package com.futsaltime.facility;

import com.futsaltime.database.models.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("facilities")
public class FacilityController {

	private IFacilityService facilityService;

	@Autowired
	public FacilityController(IFacilityService facilityService) {
		this.facilityService = facilityService;
	}

	@RequestMapping(path = "list", method = GET)
	public Collection<String> listFacilities() {
		Collection<String> facilities = new ArrayList<>();
		facilityService.retrieveAllFacilities().forEach(f -> facilities.add(f.toString()));

		return facilities;
	}
}
