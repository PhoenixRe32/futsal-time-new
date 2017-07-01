package com.futsaltime.facility;

import com.futsaltime.database.models.Facility;

public interface IFacilityService {

	Iterable<Facility> getFacilities();
}
