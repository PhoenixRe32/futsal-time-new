package com.futsaltime.bookable;

import com.futsaltime.database.models.Bookable;
import com.futsaltime.database.models.Facility;
import com.futsaltime.database.repositories.IBookableRepository;
import com.futsaltime.database.repositories.IFacilityRepository;
import com.futsaltime.exceptions.FacilityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookableService implements IBookableService {

	private IFacilityRepository facilityRepository;

	@Autowired
	public BookableService(IFacilityRepository facilityRepository) {
		this.facilityRepository = facilityRepository;
	}

	@Override
	public Iterable<Bookable> list(Long facilityId) throws FacilityNotFoundException {
		Optional<Facility> facility = facilityRepository.findById(facilityId);
		if (!facility.isPresent()) {
			throw new FacilityNotFoundException("Facility not found.");
		}

		return facility.get().getBookables();
	}
}
