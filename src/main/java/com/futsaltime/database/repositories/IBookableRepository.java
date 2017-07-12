package com.futsaltime.database.repositories;

import com.futsaltime.database.models.Bookable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IBookableRepository extends CrudRepository<Bookable, Long> {

	Iterable<Bookable> findAllByFacilityId(long facilityId);
}
