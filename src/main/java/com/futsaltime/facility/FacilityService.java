package com.futsaltime.facility;

import com.futsaltime.database.models.Facility;
import com.futsaltime.database.repositories.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService implements IFacilityService {

	private IFacilityRepository facilityRepository;

	@Autowired
	public FacilityService(IFacilityRepository facilityRepository) {
		this.facilityRepository = facilityRepository;
	}

	@Override
	public Iterable<Facility> list() {
		return facilityRepository.findAll();
	}
}
