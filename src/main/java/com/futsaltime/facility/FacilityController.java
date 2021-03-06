package com.futsaltime.facility;

import com.futsaltime.database.models.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("facilities")
public class FacilityController {

	private IFacilityService facilityService;

	@Autowired
	public FacilityController(IFacilityService facilityService) {
		this.facilityService = facilityService;
	}

	@RequestMapping(path = "list", method = GET)
	public ResponseEntity<Collection<Facility>> list() {
		Collection<Facility> facilities = new ArrayList<>();

		facilityService.list().forEach(facilities::add);

		return ResponseEntity.ok().body(facilities);
	}

	@RequestMapping(path = "add", method = POST)
	public ResponseEntity<?> add(@RequestBody FacilityDto facilityDto) {
		Facility facility = facilityService.add(facilityDto);

		return ResponseEntity.ok().body(facility);
	}
}
