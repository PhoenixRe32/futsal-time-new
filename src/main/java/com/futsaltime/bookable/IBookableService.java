package com.futsaltime.bookable;

import com.futsaltime.database.models.Bookable;
import com.futsaltime.exceptions.FacilityNotFoundException;

public interface IBookableService {

	Iterable<Bookable> list(Long facilityId) throws FacilityNotFoundException;
}
