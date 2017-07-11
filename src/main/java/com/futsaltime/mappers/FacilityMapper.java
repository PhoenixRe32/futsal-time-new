package com.futsaltime.mappers;

import com.futsaltime.database.models.Facility;
import com.futsaltime.facility.FacilityDto;

final public class FacilityMapper {


	public static Facility toModel(FacilityDto facilityDto) {
		return new Facility(facilityDto.facilityName);
	}
}
