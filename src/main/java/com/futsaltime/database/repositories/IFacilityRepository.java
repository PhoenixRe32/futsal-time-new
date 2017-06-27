package com.futsaltime.database.repositories;

import com.futsaltime.database.models.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface IFacilityRepository extends CrudRepository<Facility, Long> {

	Collection<Facility> findAllByFacilityName(String facilityName);
}
