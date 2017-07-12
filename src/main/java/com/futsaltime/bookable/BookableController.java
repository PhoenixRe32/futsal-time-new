package com.futsaltime.bookable;

import com.futsaltime.database.models.Bookable;
import com.futsaltime.exceptions.FacilityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("facilities/{id}/bookables")
public class BookableController {

	private IBookableService bookableService;

	@Autowired
	public BookableController(IBookableService bookableService) {
		this.bookableService = bookableService;
	}

	@RequestMapping(path = "list", method = GET)
	public ResponseEntity<Collection<Bookable>> list(@PathVariable("id") Long facilityId) {
		Collection<Bookable> bookables = new ArrayList<>();

		try {
			bookableService.list(facilityId).forEach(bookables::add);
		} catch (FacilityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(bookables);
	}
}
